package com.vms.server.qms.repository.dao;

import com.vms.server.domain.dto.AdmDeptDto;
import com.vms.server.domain.dto.KpiManagerDto;
import com.vms.server.domain.dto.QmsKpiSearchDto;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import com.vms.server.domain.vo.KpiManagerVo;
import com.vms.server.domain.vo.MailVo;

import java.util.List;

public interface QmsKpiDao {
    List<AdmDeptDto> getBu(String plant, String selectDept);

    List<SysSystemCodeDataDto> getfunctionType(String plant);

    List<SysSystemCodeDataDto> getRegUserList(String plant);

    List<QmsKpiSearchDto> getSearch(QmsKpiSearchDto dto);

    List<AdmDeptMappingVo> getDeptFunction(String plant, String deptId);
    List<KpiManagerVo> getKpiMaster(KpiManagerDto dto);
    List<KpiManagerVo> getRegKpiData(KpiManagerDto dto);

    List<KpiManagerVo> getKpiDel(KpiManagerDto dto);

    List<MailVo> searchInUserList(KpiManagerDto dto);

    void kpiDeleteAction(String plant, String systemName, String revision, String deleteKpi,String regYear, String mode, String deptBu, String functionType);

    String getQmsYear(String plant, String systemName, String regYear);
}
