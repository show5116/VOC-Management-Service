package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CipSearchDto {
    private String qmsNumber;
    private String regDate;
    private String process;
    private String supplier;
    private String projectTitle;
    private String projectType;
    private String status;
    private String closedDate;
    private String userName;
    private String deptName;
    private String deptId;
    private String revisionNo;
    private String closedFlag;
    private String plant;
    private String systemName;
    private String title;
    private String dept;
    private String user;
    private String fromDate;
    private String toDate;
    private String improvementItems;
    private String currentLevel;
    private String targetLevel;
    private String improvementResult;
    private String startDate;
    private String cmplDate;
    private String sysMType;
    private String sysSType;

    private List<String> projectTypes;
}
