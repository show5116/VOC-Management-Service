package com.vms.server.admin.service;

import com.vms.server.admin.dto.FileUploadRequest;
import com.vms.server.domain.vo.AttachedFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    void uploadFile(FileUploadRequest request) throws IOException;
    void deleteFile(String fileId) throws IOException;
    String getFile(String fileId);
}
