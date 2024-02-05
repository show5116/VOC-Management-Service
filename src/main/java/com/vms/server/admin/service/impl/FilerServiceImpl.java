package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.FileDao;
import com.vms.server.admin.repository.jpa.AdminQmsRfaAnalysisRepository;
import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.admin.service.FileService;
import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.QmsRfaAnalysis;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import com.vms.server.domain.vo.AttachedFileVo;
import com.vms.server.domain.vo.QmsRfaAnalysisVo;
import com.vms.server.domain.vo.QmsRfaStatusVo;
import com.vms.server.exception.CustomException;
import com.vms.server.exception.ErrorCode;
import com.vms.server.qms.repository.dao.QMSCommonDao;
import com.vms.server.qms.repository.jpa.QmsAttacheFileRepository;
import com.vms.server.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FilerServiceImpl implements FileService {


    @Value("${file.backupfolder}")
    private String BACKUP_FOLDER;

    private final AdminSysSystemCodeDataRepository adminSysSystemCodeDataRepository;
    private final QmsAttacheFileRepository qmsAttacheFileRepository;
    private final AdminQmsRfaAnalysisRepository adminQmsRfaAnalysisRepository;
    private final FileDao fileDao;
    private final QMSCommonDao commonDao;
    @PostConstruct
    public void init() {
        try {

            InetAddress localHost = InetAddress.getLocalHost();
            String hostName = localHost.getHostName();
            String hostAddress = localHost.getHostAddress();

            if (hostName.startsWith("DESKTOP-") || "localhost".equalsIgnoreCase(hostName) || "127.0.0.1".equals(hostAddress)) {
                BACKUP_FOLDER = "C:\\Users\\Admin\\Documents\\qms\\storage";
                mkdirs(BACKUP_FOLDER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public String saveFileTemp(File file, String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo) throws Exception {
        String url = "";
        try {
            String backupFolder = BACKUP_FOLDER;
            String hostName = InetAddress.getLocalHost().getHostAddress();
            String fileName = file.getName();
            String extension = FilenameUtils.getExtension(fileName);
            String baseName = FilenameUtils.getBaseName(fileName);

//            String newBackupFileName = newBackupFile.getName();
//
//            Files.move(file.toPath(), newBackupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            asfcAttachedFilesRepository.save(new AsfcAttachedFiles(plant, fileName, newBackupFileName, backupFolder, extension, issueNo,lotNumber,holdCounts,seq,step,userId));
//            url = "http://"+hostName+":9282"+File.separator+"loader"+File.separator+"getPic"+File.separator+issueNo;
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return url;
    }

    /**
     *
     * MultipartFile을 받아서 저장하고, BackUp폴더로 이동
     * @param multipartFile
     * @param plant
     * @param qmsNumber
     * @param systemName
     * @param sysMType
     * @param sysSType
     * @param revisionNo
     * @param fileDesc
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttachFile(MultipartFile multipartFile, String plant, String qmsNumber, String systemName, String sysMType, String sysSType, String revisionNo, String fileDesc) throws Exception {
        File file = null;
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                //BackUpFolder
                String backupFolder = "";
                //SystemCode에서 BackUp폴더명 조회

                List<SysSystemCodeData> sysSystemCodeDataList = adminSysSystemCodeDataRepository.findByPlantAndTableNameAndCodeName(plant, "ATTATCH_FILE_FOLDER", "STORAGE");
                if (sysSystemCodeDataList != null && !sysSystemCodeDataList.isEmpty()) {
                    backupFolder = sysSystemCodeDataList.get(0).getDescription() + File.separator + systemName + File.separator + qmsNumber;
                    //폴더 생성
                    mkdirs(backupFolder);
                }
                //MultiparFile 형태를 File 형태로 변경
                file = convertMultipartFileToFile(multipartFile);
                String fileName = file.getName();
                String extension = FilenameUtils.getExtension(fileName);
                String baseName = FilenameUtils.getBaseName(fileName);
                File newBackupFile = generateBackupFile(fileName, baseName, extension, backupFolder);
                String newBackupFileName = newBackupFile.getName();

                //File Seq 조회
                int attachedFileSeq = commonDao.getQmsAttachedFileSeq(plant,systemName,sysMType,sysSType,qmsNumber,revisionNo);
                //AttacheFile 저장
                qmsAttacheFileRepository.save(new QmsAttachFile(plant,systemName,sysMType,sysSType, qmsNumber,revisionNo,DateUtil.getCurrentDateFormatted(), "", attachedFileSeq,fileName, fileName, fileDesc, newBackupFileName,"" , "","","", "","" ));
                //File 이동
                Files.move(file.toPath(), newBackupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                file.delete();

            }
        } catch (Exception e) {
            file.delete();
            e.printStackTrace();
            throw e;
        }
    }

    public void fileValidation(File file) throws Exception {

        //파일명 Format 변경, 제품명에 '_'가 포함되는 경우가 발생함에 따라 제품명을 ()로 묶어서 변경
        //FileName Format : [yyyyMMdd]_[hhmmss]_[업체코드]_[문서번호]-[관리번호1],[관리번호2],[관리번호3]_[(제품명)].[확장자], Ex. 20230101_120000_QF000001_RFA220001-01,02,03_(SM1234_1).zip
        String fileName = file.getName();
        String device = "";
        String transDate = "";
        String transTime = "";
        String vender = "";
        String qmsNumber = "";
        String plant = "SILICONMITUS";
        String facility = "RFA";
        String analySeq = "";
        String revNo = "";
        //() 가 있는지 확인
        if (!fileName.startsWith("(") || !fileName.startsWith(")")) {
            throw new CustomException(ErrorCode.INVAILD_FILE_FORMAT.getErrorCode(), String.format(ErrorCode.INVAILD_FILE_FORMAT.getErrorMessage(), fileName));
        }
        //()안에 device명 추출
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(fileName);
        if (matcher.find()) {
            // 첫 번째 그룹이 괄호 안의 텍스트
            device = matcher.group(1);
            System.out.println("제품명: " + device);
        } else {
            throw new CustomException(ErrorCode.INVAILD_FILE_FORMAT.getErrorCode(), String.format(ErrorCode.INVAILD_FILE_FORMAT.getErrorMessage(), fileName));
        }

        //파일명에 _를 기준으로 정보가 올바르게 왔는지 확인
        String[] tempSplit = fileName.split("_");
        if (tempSplit.length != 4) {
            throw new CustomException(ErrorCode.INVAILD_FILE_FORMAT.getErrorCode(), String.format(ErrorCode.INVAILD_FILE_FORMAT.getErrorMessage(), fileName));
        }
        transDate = tempSplit[0];
        transTime = tempSplit[1];
        vender = tempSplit[2];
        qmsNumber = tempSplit[3];

        if (transDate.isEmpty() || transTime.isEmpty() || vender.isEmpty() || qmsNumber.isEmpty()) {
            throw new CustomException(ErrorCode.INVAILD_FILE_FORMAT.getErrorCode(), String.format(ErrorCode.INVAILD_FILE_FORMAT.getErrorMessage(), fileName));
        }

        String[] splitQmsNo = qmsNumber.replace(" ", "").split("-");
        if (splitQmsNo.length != 2) {
            throw new CustomException(ErrorCode.INVAILD_FILE_FORMAT.getErrorCode(), String.format(ErrorCode.INVAILD_FILE_FORMAT.getErrorMessage(), fileName));
        }
        String[] splitRevNo = splitQmsNo[1].split(",");
        List<String> lstQmsNo2 = new ArrayList<>();
        for (int i = 0; i < splitRevNo.length; i++) {
            try {
                Integer.parseInt(splitRevNo[i]);
                lstQmsNo2.add(splitRevNo[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new CustomException(ErrorCode.INVAILD_FILE_FORMAT.getErrorCode(), String.format(ErrorCode.INVAILD_FILE_FORMAT.getErrorMessage(), fileName));
            }
        }

        qmsNumber = splitQmsNo[0];
        QmsRfaStatusVo qmsRfaStatusVo = fileDao.getRafStatusInfo(plant, facility, qmsNumber, vender);
        if (qmsRfaStatusVo == null) {
            throw new CustomException(ErrorCode.RFA_DOCUMENT_NOT_FOUND.getErrorCode(), String.format(ErrorCode.RFA_DOCUMENT_NOT_FOUND.getErrorMessage(), qmsNumber));
        }

        if (!device.equals(qmsRfaStatusVo.getDevice())) {
            throw new CustomException(ErrorCode.RFA_NOT_MATCH_DEVICE.getErrorCode(), String.format(ErrorCode.RFA_NOT_MATCH_DEVICE.getErrorMessage(), qmsNumber));
        }
        if (!vender.equals(qmsRfaStatusVo.getFaSubc())) {
            throw new CustomException(ErrorCode.RFA_NOT_MATCH_SUBCONTRACTOR.getErrorCode(), String.format(ErrorCode.RFA_NOT_MATCH_SUBCONTRACTOR.getErrorMessage(), qmsNumber));
        }

        List<QmsRfaAnalysis> qmsRfaAnalysisList = adminQmsRfaAnalysisRepository.findByPlantAndSystemNameAndQmsNumber(plant, facility, qmsNumber);
        List<String> qmsRfaAnalysisSeqList = new ArrayList<>();
        for (QmsRfaAnalysis qmsRfaAnalysis : qmsRfaAnalysisList) {
            qmsRfaAnalysisSeqList.add(qmsRfaAnalysis.getSeq());
        }

        for (String revNoTemp : lstQmsNo2) {
            if (!qmsRfaAnalysisSeqList.contains(revNoTemp)) {
                throw new CustomException(ErrorCode.RFA_NOT_MATCH_REQUEST_NUMBER.getErrorCode(), String.format(ErrorCode.RFA_NOT_MATCH_REQUEST_NUMBER.getErrorMessage(), qmsNumber + "-" + String.format("%02d", Integer.parseInt(revNoTemp))));
            }
        }
        List<QmsRfaAnalysisVo> analysisVoList = fileDao.getAnalySeq(plant, facility, qmsNumber, lstQmsNo2);
        if (analysisVoList != null && !analysisVoList.isEmpty()) {
            analySeq = analysisVoList.get(0).getFileSeq();
        }
        revNo = qmsRfaStatusVo.getRevisionNo();


    }

    @Override
    public void deleteFiles(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo) throws Exception {
        try {
            List<QmsAttachFile> qmsAttachFileList = qmsAttacheFileRepository.findByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(plant, systemName, systemNameMtype, systemNameStype, qmsNumber, revisionNo);

            for (QmsAttachFile qmsAttachFile : qmsAttachFileList) {
                String filePath = qmsAttachFile.getFilePath();
                File file = new File(filePath);
                // 파일이 존재하는지 확인하고 삭제
                if (file.exists()) {
                    if (file.delete()) {
                        System.out.println("파일이 성공적으로 삭제되었습니다: " + filePath);
                    } else {
                        System.out.println("파일 삭제에 실패하였습니다.");
                    }
                } else {
                    System.out.println("파일이 존재하지 않습니다: " + filePath);
                }
            }
            qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(plant, systemName, systemNameMtype, systemNameStype, qmsNumber, revisionNo);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }

    @Override
    public AttachedFileVo getAttachedFile(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo, int fileSeq) {


        QmsAttachFile qmsAttachFile = qmsAttacheFileRepository.findByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNoAndFileSeq(plant, systemName, systemNameMtype, systemNameStype, qmsNumber, revisionNo, fileSeq);
        String filePath = qmsAttachFile.getFilePath();

        File file = new File(filePath);

        return new AttachedFileVo(file, qmsAttachFile.getOrgFileId());
    }

    @Override
    public List<AttachedFileVo> getAttachedFiles(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo) {

        List<AttachedFileVo> result = new ArrayList<>();
        List<QmsAttachFile> qmsAttachFileList = qmsAttacheFileRepository.findByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(plant, systemName, systemNameMtype, systemNameStype, qmsNumber, revisionNo);
        if (qmsAttachFileList != null && !qmsAttachFileList.isEmpty()) {
            for (QmsAttachFile qmsAttachFile : qmsAttachFileList) {
                String filePath = qmsAttachFile.getFilePath();
                File file = new File(filePath);
                result.add(new AttachedFileVo(file, qmsAttachFile.getOrgFileId()));
            }
        }
        return result;
    }

    public void mkdirs(String folderPath) {

        File folder = new File(folderPath);

        // 폴더가 존재하지 않는 경우 생성
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("폴더가 성공적으로 생성되었습니다: " + BACKUP_FOLDER);
            } else {
                System.out.println("폴더 생성에 실패하였습니다.");
            }
        } else {
            System.out.println("폴더가 이미 존재합니다: " + BACKUP_FOLDER);
        }
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return file;
    }

    private File generateBackupFile(String fileName, String baseName, String extension, String backupFolder) {

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        File newBackupFile = new File(backupFolder + File.separator + baseName + "_" + currentDate + "." + extension);
        int counter = 1;

        // 기존에 같은 이름의 파일이 이미 존재한다면, 괄호 안에 숫자를 추가하여 중복을 피함
        while (newBackupFile.exists()) {
            newBackupFile = new File(backupFolder, baseName + "_" + currentDate + "(" + counter + ")." + extension);
            counter++;
        }

        return newBackupFile;
    }
}
