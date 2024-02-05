package com.vms.server.domain.dto;

import com.vms.server.domain.vo.KpiManagerVo;
import com.vms.server.domain.vo.SysSystemCodeDataVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QmsKpiResultSearchDto {
    private String plant;
    private String systemName;
    private String docTitle;
    private String docNo;
    private String deptBu;
    private String dept;
    private String deptFunction;
    private String drafter;
    private String gubun;
    private String process;
    private String subcontract;
    private boolean myDoc;
    private boolean isInput;
    private String userId;
    private String userRole;
    private List<String> regYear;
    private List<KpiManagerVo> dtList;
    private List<SysSystemCodeDataVo> cycleList;
}
