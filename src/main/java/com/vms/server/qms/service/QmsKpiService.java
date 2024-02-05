package com.vms.server.qms.service;

import com.vms.server.domain.dto.*;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import com.vms.server.domain.vo.KpiManagerVo;
import com.vms.server.domain.vo.SysSystemCodeDataVo;

import java.util.List;

public interface QmsKpiService {

    List<SysSystemCodeDataVo> getRegYearList(String plant, String systemName);

    List<SysSystemCodeDataVo> getKpiCycleList(String plant, String tableName);

    List<QmsDocumentStatusDto> getDocName(String plant, String titleSearch);

    List<AdmDeptDto> getBu(String plant, String selectDept);

    List<SysSystemCodeDataDto> getfunctionType(String plant);

    List<SysSystemCodeDataDto> getRegUserList(String plant);

    List<QmsKpiSearchDto> getSearch(QmsKpiSearchDto dto);

    List<SysSystemCodeDataVo> getKpiCycleList(String plant);

    List<AdmDeptMappingVo> getDeptFunction(String plant, String deptId);

    void kpiAction(KpiManagerDto dto);

    List<KpiManagerVo> getMailGrid(KpiManagerDto dto);

    List<SysSystemCodeDataVo> setKpiType(SysSystemCodeDataDto dto);

    List<KpiManagerVo> getKpiDel(KpiManagerDto dto);

    List<KpiManagerVo> getKpiMaster(KpiManagerDto dto);

    List<KpiManagerVo> getRegKpiData(KpiManagerDto dto);




}
