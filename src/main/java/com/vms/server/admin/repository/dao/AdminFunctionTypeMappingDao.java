package com.vms.server.admin.repository.dao;

import com.vms.server.domain.vo.AdmDeptMappingVo;

import java.util.List;

public interface AdminFunctionTypeMappingDao {

    List<AdmDeptMappingVo> getFunctionTypeMappingData(String plant, String selectDept);
}
