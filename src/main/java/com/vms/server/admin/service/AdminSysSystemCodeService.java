package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysSystemCodeAndDataDto;
import com.vms.server.domain.dto.SysSystemCodeDto;

import java.util.List;

public interface AdminSysSystemCodeService {

    List<SysSystemCodeDto> getSysSystemCode(SysSystemCodeDto sysSystemCodeDto);

    void insertSysSystemCode(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto);

    void updateSysSystemCode(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto);

    void deleteSysSystemCode(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto);
}
