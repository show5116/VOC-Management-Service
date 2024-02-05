package com.vms.server.domain.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsAttachFileVo {
    private String plant;
    private String systemName;
    private String systemNameMtype;
    private String systemNameStype;
    private String qmsNumber;
    private String revisionNo;
    private String createTime;
    private String fileType;
    private String fileSeq;
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
}
