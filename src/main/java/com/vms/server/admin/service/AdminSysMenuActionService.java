package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysMenuActionDto;

import java.util.List;

public interface AdminSysMenuActionService {

    List<SysMenuActionDto> getSysMenuAction(SysMenuActionDto sysMenuActionDto);

    void insertSysMenuAction(SysMenuActionDto sysMenuActionDto);

    void updateSysMenuAction(SysMenuActionDto sysMenuActionDto);

    void deleteSysMenuAction(SysMenuActionDto sysMenuActionDto);

    List<SysMenuActionDto> findSysMenuAction(SysMenuActionDto sysMenuActionDto);
}
