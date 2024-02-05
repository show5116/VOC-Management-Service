package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysMenuGroupDto;
import com.vms.server.domain.vo.SysMenuVo;

import java.util.List;

public interface AdminSysMenuGroupService {

    List<SysMenuGroupDto> getSysMenuGroup(SysMenuGroupDto sysMenuGroupDto);

    List<SysMenuGroupDto> getConditionModule(SysMenuGroupDto sysMenuGroupDto);

    List<SysMenuVo> getMenuTree(SysMenuGroupDto sysMenuGroupDto);

    void insertSysMenuGroup(SysMenuGroupDto sysMenuGroupDto);

    void deleteSysMenuGroup(SysMenuGroupDto sysMenuGroupDto);

    List<SysMenuGroupDto> getGroupId(SysMenuGroupDto sysMenuGroupDto);

}
