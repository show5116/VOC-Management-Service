package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysMenuStructureDto;

import java.util.List;

public interface AdminSysMenuStructureService {
    void insertSysMenuStructure(List<SysMenuStructureDto> sysMenuStructureDtos);

    void deleteSysMenuStructure(List<SysMenuStructureDto> sysMenuStructureDtos);
}
