package com.vms.server.admin.service;

import com.vms.server.domain.vo.ViewerVo;

public interface FileViewerService {
    byte[] convertDocxToPdf(String sourceDocx);

    ViewerVo readFile(String filePath) throws Exception;
}
