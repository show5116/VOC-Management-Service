package com.vms.server.domain.dto;

import com.vms.server.domain.vo.KpiManagerVo;
import com.vms.server.domain.vo.SysSystemCodeDataVo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KpiManagerDto {
    private String plant;
    private String systemName;
    private String userId;
    private String revision;
    private String qmsNumber;
    private String mode;
    private String deptBu;
    private String deptBuName;
    private String functionType;
    private String regYear;
    private String deleteKpi;
    private String userName;
    private String userDept;
    private String kpiType;
    private boolean isReg;
    private List<KpiManagerVo> dtList;
    private List<SysSystemCodeDataVo> cycleList;

}
