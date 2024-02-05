package com.vms.server.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LsActionDto {
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
    private String updateDate;
    private String updateUser;
    private String closedFlag;
    private String closedDate;
    private String closedUser;
    private String measures;
    private String sysMType;
    private String sysSType;
    private String user;
    private List<String> productList;
    private List<QmsLsDetailDto> qmsLsDetailDtoList;
    private List<QmsAttachFileDto> qmsAttachFileDtoList;
}
