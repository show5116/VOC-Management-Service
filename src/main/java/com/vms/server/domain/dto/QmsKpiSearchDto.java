package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QmsKpiSearchDto {
    private String regDate;
    private String qmsNumber;
    private String deptBu;
    private String deptId;
    private String type;
    private String process;
    private String classification;
    private String docNumber;
    private String docTitle;
    private String kpi;
    private String cycle;
    private String calcFormula;
    private String unit;
    private String regUser;
    private String functionType;
    private String deptBuR;
    private String functionTypeR;
    private String typeR;
    private String regYearR;

    private String plant;
    private String systemName;
    private String docNo;
    private String dept;
    private String deptFunction;
    private String drafter;
    private String gubun;
    private List<String> regYear;

}

