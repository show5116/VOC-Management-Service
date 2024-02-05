package com.vms.server.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsAttachFileDto {
    private String plant;
    private String systemName;
    private String systemNameMtype;
    private String systemNameStype;
    private String qmsNumber;
    private String revisionNo;
    private String createTime;
    private String fileType;
    private Integer fileSeq;
    private String realFileId;
    private String orgFileId;
    private String fileRemark;
    private String filePath;
    private String fileExistYn;
    private String expandField1;
    private String expandField2;
    private String expandField3;
    private String expandField4;
    private String expandField5;
    private String fileName;
    private String fileDesc;
    private String remove ="";
    private String date;
    private String approveDate;
    private String seq ="";
    private String remark;
    private String filePathR;
    private MultipartFile multipartFile;



}
