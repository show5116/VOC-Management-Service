package com.vms.server.admin.controller;

import com.vms.server.admin.dto.FileUploadRequest;
import com.vms.server.admin.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/file")
    public ResponseEntity<Void> addFile(FileUploadRequest request) throws IOException {
        fileService.uploadFile(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/file")
    public ResponseEntity<InputStreamResource> downloadFIle(@RequestParam String fileId) throws IOException {
        FileSystemResource fileSystemResource = new FileSystemResource(fileService.getFile(fileId));
        File file = fileSystemResource.getFile();

        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(file.getName(), StandardCharsets.UTF_8)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(fileSystemResource.contentLength())
                .headers(headers)
                .body(new InputStreamResource(fileSystemResource.getInputStream()));
    }

    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@RequestParam String fileId) throws IOException {
        fileService.deleteFile(fileId);
        return ResponseEntity.ok().build();
    }

}
