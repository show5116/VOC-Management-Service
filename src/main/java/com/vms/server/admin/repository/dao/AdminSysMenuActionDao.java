package com.vms.server.admin.repository.dao;

import com.vms.server.domain.dto.SysMenuActionDto;

import java.util.List;

public interface AdminSysMenuActionDao {
    List<SysMenuActionDto> getSysMenuAction(SysMenuActionDto sysMenuActionDto);

    int getActionSeq(SysMenuActionDto sysMenuActionDto);
}
