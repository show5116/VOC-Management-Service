package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysSystemCodeAndDataDto;
import com.vms.server.domain.dto.SysSystemCodeDataDto;

import java.util.List;

public interface AdminSysSystemCodeDataService {
    List<SysSystemCodeDataDto> getModule(SysSystemCodeDataDto sysSystemCodeDataDto);

    List<SysSystemCodeDataDto> getSysSystemCodeData(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto);

    List<SysSystemCodeDataDto> getApplicationCd(String plant, String tableName);
}
