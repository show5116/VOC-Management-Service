package com.vms.server.admin.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter @Setter
public class FileUploadRequest {
    private List<MultipartFile> files;
    private String plant;
    private String systemName;
    private String systemNameMtype;
    private String systemNameStype;
    private String qmsNumber;
    private String revisionNo;
}
