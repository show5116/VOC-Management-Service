package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysClientHistoryDto;

import java.util.List;

public interface AdminSysClientHistoryService {
    List<SysClientHistoryDto> getAdminSysClientHistory(SysClientHistoryDto sysClientHistoryDto);
}
