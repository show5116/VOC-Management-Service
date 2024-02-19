package com.vms.server.admin.service.impl;

import com.vms.server.admin.dto.FileUploadRequest;
import com.vms.server.admin.repository.dao.FileDao;
import com.vms.server.admin.repository.jpa.AdminQmsRfaAnalysisRepository;
import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.admin.service.FileService;
import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.QmsRfaAnalysis;
import com.vms.server.domain.entity.qms.id.QmsAttachFileId;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import com.vms.server.domain.vo.AttachedFileVo;
import com.vms.server.domain.vo.QmsRfaAnalysisVo;
import com.vms.server.domain.vo.QmsRfaStatusVo;
import com.vms.server.exception.CustomException;
import com.vms.server.exception.ErrorCode;
import com.vms.server.qms.repository.dao.QMSCommonDao;
import com.vms.server.qms.repository.jpa.QmsAttacheFileRepository;
import com.vms.server.util.DateUtil;
import com.vms.server.voc.repository.QmsVocStatusRepository;
import com.vms.server.voc.service.VocService;
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
import java.util.UUID;
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
    private final QmsVocStatusRepository qmsVocStatusRepository;
    private final FileDao fileDao;
    private final QMSCommonDao commonDao;
    @PostConstruct
    public void init() {
        try {

            InetAddress localHost = InetAddress.getLocalHost();
            String hostName = localHost.getHostName();
            String hostAddress = localHost.getHostAddress();

            if (hostName.startsWith("DESKTOP-") || "localhost".equalsIgnoreCase(hostName) || "127.0.0.1".equals(hostAddress)) {
                BACKUP_FOLDER = "C:\\Users\\show5\\Documents\\qms\\storage";
                mkdirs(BACKUP_FOLDER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional
    public void uploadFile(FileUploadRequest request) throws IOException, IllegalStateException {
        MultipartFile multipartFile = request.getFile();

        if (multipartFile == null) {
            throw new IllegalArgumentException("No files received");
        }

        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));

        String uniqueName  = UUID.randomUUID() + fileExtension;
        String filePath = BACKUP_FOLDER + "\\" + uniqueName;

        File saveFile = new File(filePath);

        multipartFile.transferTo(saveFile);

        QmsAttachFile qmsAttachFile = QmsAttachFile.builder(new QmsAttachFileId(request.getPlant(), request.getSystemName(), request.getSystemNameMtype(), request.getSystemNameStype(), request.getQmsNumber(), request.getRevisionNo(), "1"))
                .realFileId(uniqueName)
                .orgFileId(fileName)
                .filePath(filePath)
                .fileExistYn(true)
                .build();

        qmsAttacheFileRepository.save(qmsAttachFile);
    }

    @Override
    public String getFile(String fileId) {
        QmsAttachFile qmsAttachFile = qmsAttacheFileRepository.findFirstByRealFileId(fileId);

        return qmsAttachFile.getFilePath();
    }

    /*
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
    */
    private void mkdirs(String folderPath) {

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
}
