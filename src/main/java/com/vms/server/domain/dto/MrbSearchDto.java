package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MrbSearchDto {

    private String plant;
    private String systemName;
    private String txtTitle;
    private String txtProduct;
    private String txtRegUser;
    private String txtSupplier;
    private List<String> dscbPhen;
    private List<String> dscbCause;
    private String startDate;
    private String endDate;
    private String userId;
    private String mode;
    private boolean chkMyDocument;
    private boolean chkExternal;
    private List<String> issueOper;
    private String txtQmsNumber;
    private String qmsNumber;
    private String regDate;
    private String issueTitle;
    private String issueTime;
    private String device;
    private String issueProcess;
    private String issueSupplier;
    private String faultyPhenomenon;
    private String faultyCause;
    private String externalFlag;
    private String currentStepCode;
    private String currentStep;
    private String approvalDate;
    private String availabilityFlag;
    private String closedDate;
    private String regUser;

}
