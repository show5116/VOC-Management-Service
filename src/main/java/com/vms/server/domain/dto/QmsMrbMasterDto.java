package com.vms.server.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsMrbMasterDto {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String issueTime;
    private String issueTitle;
    private String issueProcess;
    private String issueSupplier;
    private String faultyPhenomenon;
    private String faultyCause;
    private String measures;
    private String device;
    private String problemDisposiotion;
    private String externalFlag;
    private String availabilityFlag;
    private String closedFlag;
    private String rootCause;
    private String regDate;
    private String regUser;
    private String closedDate;
    private String closedUser;
    private String currentStep;
    private String lotDisposiotion;
    private String unnecessaryReason;
}
