package com.vms.server.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsLsMasterDto {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String issueTitle;
    private String division;
    private String department;
    private String issueCategory;
    private String process;
    private String supplier;
    private String cause;
    private String issueSummary;
    private String causeComment;
    private String regDate;
    private String regUser;
    private String regUserName;
    private String updateDate;
    private String updateUser;
    private String closedFlag;
    private String closedDate;
    private String closedUser;
    private String measures;
    private String userDept;
    private String deptName;
}
