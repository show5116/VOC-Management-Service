package com.vms.server.qms.repository.dao;

import com.vms.server.domain.dto.QmsKpiResultSearchDto;
import com.vms.server.domain.vo.AdmDeptVo;

import java.util.List;
import java.util.Map;

public interface QmsKpiResultDao {
    List<AdmDeptVo> getDept(String plant, String selectDept);

    List<Map<String, Object>> getSearch(QmsKpiResultSearchDto dto);
}
