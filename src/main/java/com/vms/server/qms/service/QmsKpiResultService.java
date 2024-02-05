package com.vms.server.qms.service;

import com.vms.server.domain.dto.QmsKpiResultSearchDto;
import com.vms.server.domain.vo.AdmDeptVo;
import com.vms.server.domain.vo.QmsKpiResultStatusVo;

import java.util.List;
import java.util.Map;

public interface QmsKpiResultService {

    List<String> getDocNumber(String plant, String titleSearch);

    List<AdmDeptVo> getDept(String plant, String selectDept);

    List<Map<String, Object>> getSearch(QmsKpiResultSearchDto dto);

    List<QmsKpiResultStatusVo> getResultStatus(String plant, String systemName, String regYear, String kpiType, String deptBu, String functionType, String process, String subcontract);
}
