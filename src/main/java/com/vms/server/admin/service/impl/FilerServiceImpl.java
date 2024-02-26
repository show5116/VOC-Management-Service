package com.vms.server.admin.service.impl;

import com.vms.server.admin.dto.FileUploadRequest;
import com.vms.server.admin.service.FileService;
import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.id.QmsAttachFileId;
import com.vms.server.qms.repository.jpa.QmsAttacheFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilerServiceImpl implements FileService {
    @Value("${file.backupfolder}")
    private String BACKUP_FOLDER;

    private final QmsAttacheFileRepository qmsAttacheFileRepository;
    private final List<String> imageExtensions = List.of("jpg", "jpeg", "png", "bmp");

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
        List<MultipartFile> multipartFiles = request.getFiles();

        List<QmsAttachFile> qmsAttachFiles = new ArrayList<>();

        String maxFileSeq = qmsAttacheFileRepository.findMaxFileSeqByQmsNumber(request.getQmsNumber());

        int fileSeq = maxFileSeq != null ? Integer.parseInt(maxFileSeq) : 0;

        for (MultipartFile multipartFile : multipartFiles) {
            fileSeq++;
            String fileName = multipartFile.getOriginalFilename();
            assert fileName != null;
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));

            String uniqueName = newFileId(fileExtension);
            String filePath = BACKUP_FOLDER + "\\" + uniqueName;

            File saveFile = new File(filePath);

            multipartFile.transferTo(saveFile);

            String thumbnail = null;
                // makeThumbnail(saveFile, fileExtension.substring(1));
                // todo: lob 형태의 컬럼 필요

            QmsAttachFile qmsAttachFile = QmsAttachFile.builder(
                    new QmsAttachFileId(request.getPlant(),
                            request.getSystemName(),
                            request.getSystemNameMtype(),
                            request.getSystemNameStype(),
                            request.getQmsNumber(),
                            request.getRevisionNo(),
                            String.valueOf(fileSeq)))
                    .realFileId(uniqueName)
                    .orgFileId(fileName)
                    .filePath(filePath)
                    .fileExistYn(true)
                    .expandField1(thumbnail)
                    .build();

            qmsAttachFiles.add(qmsAttachFile);
        }

        qmsAttacheFileRepository.saveAll(qmsAttachFiles);
    }

    @Override
    @Transactional
    public void deleteFile(String fileId) throws IOException {
        QmsAttachFile qmsAttachFile = qmsAttacheFileRepository.findFirstByRealFileId(fileId);
        String filePath = qmsAttachFile.getFilePath();
        File file = new File(filePath);
        if (!file.delete()) {
            throw new IOException("file delete failed");
        }
        qmsAttacheFileRepository.delete(qmsAttachFile);
    }

    @Override
    public String getFile(String fileId) {
        QmsAttachFile qmsAttachFile = qmsAttacheFileRepository.findFirstByRealFileId(fileId);

        return qmsAttachFile.getFilePath();
    }

    private String makeThumbnail(File file, String fileExtension){
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        if (!imageExtensions.contains(fileExtension)) {
            return null;
        }

        BufferedImage inputImage = null;

        try {
            inputImage = ImageIO.read(file);
        } catch (IOException e) {
            return null;
        }

        Image tmp = inputImage.getScaledInstance(38, 38, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(38, 38, inputImage.getType());

        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        try {
            ImageIO.write(resized, fileExtension, os);
        } catch (IOException e) {
            return null;
        }

        return Base64.getEncoder().encodeToString(os.toByteArray());
    }

    private String newFileId(String fileExtension) {
        String fileId = UUID.randomUUID() + fileExtension;
        while (qmsAttacheFileRepository.findFirstByRealFileId(fileId) != null) {
            fileId = UUID.randomUUID() + fileExtension;
        }
        return fileId;
    }

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
