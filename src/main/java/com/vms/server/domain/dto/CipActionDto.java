package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CipActionDto {
    private String plant;
    private String systemName;
    private String sysMType;
    private String sysSType;
    private String dml;
    private String qmsNumber;
    private String revisionNo;
    private String projectTitle;
    private List<String> projectTypes;
    private String process;
    private String supplier;
    private String improvementItems;
    private String improvementResult;
    private String currentLevel;
    private String targetLevel;
    private String issueDate;
    private String estimatedCmplDate;
    private String completeDate;
    private String closedFlag;
    private String userid;

    private List<QmsAttachFileDto> qmsAttachFileDtoList;

}
