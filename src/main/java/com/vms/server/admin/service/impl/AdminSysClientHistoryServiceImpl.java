package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminAdmClientHistoryDao;
import com.vms.server.admin.service.AdminSysClientHistoryService;
import com.vms.server.domain.dto.SysClientHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdminSysClientHistoryServiceImpl implements AdminSysClientHistoryService {

    private final AdminAdmClientHistoryDao adminAdmClientHistoryDao;

    @Override
    public List<SysClientHistoryDto> getAdminSysClientHistory(SysClientHistoryDto sysClientHistoryDto) {
        return adminAdmClientHistoryDao.getAdmClientHistory(sysClientHistoryDto);
    }
}
