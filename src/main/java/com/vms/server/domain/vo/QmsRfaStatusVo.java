package com.vms.server.domain.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsRfaStatusVo {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String currentStep;
    private String faPurpose;
    private String device;
    private String mvtVer;
    private String prdType;
    private String faSubc;
    private String faManager;
    private String totalQty;
    private String degree;
    private String fabSubc;
    private String techNode;
    private String customer;
    private String rmaNumber;
    private String remark;
    private String regDate;
    private String regUser;
    private String updateDate;
    private String updateUser;
    private String closedFlag;
    private String closedDate;
    private String closedUser;
    private String toEmail;
    private String ccEmail;
    private String systemGroup;
    private String systemDesc;
}
