package com.vms.server.admin.repository.dao;

import com.vms.server.domain.dto.SysMenuDto;
import com.vms.server.domain.dto.SysMenuGroupDto;

import java.util.List;

public interface AdminSysMenuGroupDao {

    List<SysMenuGroupDto> getSysMenuGroup(SysMenuGroupDto sysMenuGroupDto);

    List<SysMenuDto> getMenuTree(SysMenuGroupDto sysMenuGroupDto);

    List<SysMenuGroupDto> getConditionModule(SysMenuGroupDto sysMenuGroupDto);
}
