package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.dto.SysSystemCodeDto;
import com.vms.server.domain.entity.sys.SysSystemCode;

import java.util.List;

public interface AdminSysSystemCodeQueryRepository {

    List<SysSystemCode> getSysSystemCode(SysSystemCodeDto sysSystemCodeDto);
}
