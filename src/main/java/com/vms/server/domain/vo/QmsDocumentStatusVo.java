package com.vms.server.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsDocumentStatusVo {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String currentStep;
    private String docType;
    private String workType;
    private String docTitle;
    private String remark;
    private String expirationDate;
    private String expireFlag;
    private String returnRemark;
    private String regDate;
    private String regUser;
    private String validationFlag;
    private String prenumFlag;
}
