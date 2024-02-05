package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminSysFileLogDao;
import com.vms.server.admin.service.AdminSysFileLogService;
import com.vms.server.domain.dto.SysFileLogDto;
import com.vms.server.domain.vo.SysFileLogVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminSysFileLogServiceImpl implements AdminSysFileLogService {

    private final AdminSysFileLogDao adminSysFileLogDao;

    @Override
    public List<SysFileLogVo> getSysFileLog(SysFileLogDto sysFileLogDto) {
        List<SysFileLogDto> sysFileLogList = adminSysFileLogDao.getSysFileLog(sysFileLogDto);
        return sysFileLogList.stream().map(sysFileLog -> new SysFileLogVo(
                Optional.ofNullable(sysFileLog.getUserId()).orElse(Collections.emptyList()).stream().collect(Collectors.joining(", ")),
                sysFileLog.getUserName(),
                sysFileLog.getDeptName(),
                sysFileLog.getStatus(),
                sysFileLog.getIpAddr(),
                sysFileLog.getFileName(),
                sysFileLog.getTransTime()
        )).collect(Collectors.toList());
    }
}
