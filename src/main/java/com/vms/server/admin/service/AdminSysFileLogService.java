package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysFileLogDto;
import com.vms.server.domain.vo.SysFileLogVo;

import java.util.List;

public interface AdminSysFileLogService {
    List<SysFileLogVo> getSysFileLog(SysFileLogDto sysFileLogDto);
}
