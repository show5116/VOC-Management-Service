package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmDeptDto;
import com.vms.server.domain.dto.AdmDeptMappingDto;
import com.vms.server.domain.vo.AdmDeptMappingVo;

import java.util.List;

public interface AdminFunctionTypeMappingService {
    List<AdmDeptMappingVo> getFunctionTypeMappingData(String plant, String selectDept);

    void UpdateDepartmentFunction(String plant, String regUser, List<AdmDeptMappingDto> deptMappingDtoList);

    List<AdmDeptDto>  getUserDepartment();
}
