package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MrbManagerDto {

    private String qmsNumber;
    private String revisionNo;
    private String issueTitle;
    private String device;
    private String issueTime;
    private String issueProcess;
    private String issueSupplier;
    private String problemDisposition;
    private String rootCause;
    private String faultyPhenomenon;
    private String faultyCause;
    private String lotDisposition;
    private String externalFlag;
    private String availabilityFlag;
    private String closedFlag;
    private String regDate;
    private String regUser;
    private String closedDate;
    private String closedUser;
    private String currentStep;
    private String empName;
    private String deptName;
    private String unnecessaryReason;
    private String plant;
    private String userId;
    private String systemName;
    private String mrbNumber;
    private boolean close;
    private boolean drop;
    private String comment;
    private String today;
    private String title;
    private int approvalRow;
    private String issueDate;
    private String process;
    private String site;
    private String problemDesc;
    private String causeDesc;
    private String problem;
    private String cause;
    private boolean external;
    private boolean validation;
    private String reason;
    private boolean insert;
    private String stepFlag;
    private String changeStep;
    private String mailFlag;
    private String step;
    private String changeUser;
    private String remark;
    List<QmsMrbLotListDto> nonConfList;
    List<QmsActionItemListDto> actionItemList;
    List<QmsAttachFileDto> customerReportList;
    List<QmsAttachFileDto> validationList;
    List<QmsAttachFileDto> reportList;
    List<QmsApprovalRuleDto> approvalList;
    List<CommonUserInfoDto> meberList;
}
