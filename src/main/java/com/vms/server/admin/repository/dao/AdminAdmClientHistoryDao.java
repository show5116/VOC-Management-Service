package com.vms.server.admin.repository.dao;

import com.vms.server.domain.dto.SysClientHistoryDto;

import java.util.List;

public interface AdminAdmClientHistoryDao {
    List<SysClientHistoryDto> getAdmClientHistory(SysClientHistoryDto sysClientHistoryDto);
}
