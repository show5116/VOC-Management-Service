package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmDeptDto;
import com.vms.server.domain.vo.AdmDeptMappingVo;

import java.util.List;

public interface HrService {

    List<AdmDeptDto> getDept();

    List<AdmDeptMappingVo> getDeptByDeptName(String deptName);
}
