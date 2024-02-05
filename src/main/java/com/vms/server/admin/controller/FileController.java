package com.vms.server.admin.controller;

import com.vms.server.admin.service.FileService;
import com.vms.server.domain.dto.QmsAttachFileDto;
import com.vms.server.domain.vo.AttachedFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload/files")
    public ResponseEntity<String> uploadIssueFiles(
            @RequestParam("fileList") List<MultipartFile> fileList,
            @RequestParam("plant") String plant,
            @RequestParam("systemName") String systemName,
            @RequestParam("qmsNumber") String qmsNumber
    ) {

        if (fileList.isEmpty()) {
            return new ResponseEntity<>("No files received", HttpStatus.BAD_REQUEST);
        }

        try {
            //fileService.saveFiles(fileList,plant, systemName,qmsNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Fail to upload", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Files successfully uploaded!", HttpStatus.OK);
    }

    @PostMapping("/download/file")
    public void downloadFile(@RequestBody QmsAttachFileDto dto, HttpServletResponse response) throws IOException {

        AttachedFileVo file = fileService.getAttachedFile(dto.getPlant(),dto.getSystemName(),dto.getSystemNameMtype(),dto.getSystemNameStype(),dto.getQmsNumber(), dto.getRevisionNo(), dto.getFileSeq());

        // 첫 번째 파일만 다운로드하도록 설정
        if (file != null) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "UTF-8"));

            try (InputStream is = new FileInputStream(file.getFile());
                 OutputStream os = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
            // 파일이 없는 경우의 처리 (예: 오류 메시지 반환)
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("File not found");
        }
    }
    @PostMapping("/download/files")
    public void downloadFilesInZip(@RequestBody  QmsAttachFileDto dto , HttpServletResponse response) throws IOException {

        List<AttachedFileVo> files = fileService.getAttachedFiles(dto.getPlant(),dto.getSystemName(),dto.getSystemNameMtype(),dto.getSystemNameStype(),dto.getQmsNumber(), dto.getRevisionNo());
        // ZIP 파일 이름 설정
        String randomPart = UUID.randomUUID().toString().substring(0, 8); // 첫 8자리만 사용
        String zipFileName = "log_files_" + randomPart + ".zip";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + zipFileName);

        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            for (AttachedFileVo attachedFileVo : files) {
                try (FileInputStream fis = new FileInputStream(attachedFileVo.getFile())) {
                    ZipEntry zipEntry = new ZipEntry(attachedFileVo.getFileName());
                    zos.putNextEntry(zipEntry);
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zos.write(bytes, 0, length);
                    }
                }
                zos.closeEntry();
            }
        }
    }
}
