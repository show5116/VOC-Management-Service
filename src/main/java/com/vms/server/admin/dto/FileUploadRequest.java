package com.vms.server.admin.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class FileUploadRequest {
    private MultipartFile file;
    private String plant;
    private String systemName;
    private String systemNameMtype;
    private String systemNameStype;
    private String qmsNumber;
    private String revisionNo;

}
