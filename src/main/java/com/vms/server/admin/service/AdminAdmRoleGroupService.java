package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmRoleGroupDto;

import java.util.List;

public interface AdminAdmRoleGroupService {
    List<AdmRoleGroupDto> getAdmRoleGroup(AdmRoleGroupDto admRoleGroupDto);
}
