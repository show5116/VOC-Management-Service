package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminSysMenuActionDao;
import com.vms.server.admin.repository.jpa.AdminSysMenuActionRepository;
import com.vms.server.admin.service.AdminSysMenuActionService;
import com.vms.server.domain.dto.SysMenuActionDto;
import com.vms.server.domain.entity.sys.SysMenuAction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminSysMenuActionServiceImpl implements AdminSysMenuActionService {

    private final AdminSysMenuActionRepository adminSysMenuActionRepository;
    private final AdminSysMenuActionDao adminSysMenuActionDao;
    private final ModelMapper modelMapper;

    @Override
    public List<SysMenuActionDto> getSysMenuAction(SysMenuActionDto sysMenuActionDto) {
        return adminSysMenuActionDao.getSysMenuAction(sysMenuActionDto);
    }

    @Override
    public void insertSysMenuAction(SysMenuActionDto sysMenuActionDto) {
        if (sysMenuActionDto != null) {
            int actionSeq = adminSysMenuActionDao.getActionSeq(sysMenuActionDto);
            sysMenuActionDto.setActionSeq(actionSeq);

            SysMenuAction sysMenuAction = modelMapper.map(sysMenuActionDto, SysMenuAction.class);
            adminSysMenuActionRepository.save(sysMenuAction);
        }
    }

    @Override
    public void updateSysMenuAction(SysMenuActionDto sysMenuActionDto) {
        if (sysMenuActionDto != null) {
            SysMenuAction sysMenuAction = modelMapper.map(sysMenuActionDto, SysMenuAction.class);
            adminSysMenuActionRepository.save(sysMenuAction);
        }
    }

    @Override
    public void deleteSysMenuAction(SysMenuActionDto sysMenuActionDto) {
        String actionSeq = String.valueOf(sysMenuActionDto.getActionSeq());
        adminSysMenuActionRepository.deleteById(actionSeq);
    }

    @Override
    public List<SysMenuActionDto> findSysMenuAction(SysMenuActionDto sysMenuActionDto) {
        String actionSeq = String.valueOf(sysMenuActionDto.getActionSeq());
        List<SysMenuActionDto> result = new ArrayList<>();
        List<SysMenuAction> sysMenuActions = adminSysMenuActionRepository.findByActionSeq(actionSeq);
        sysMenuActions.forEach(sysMenuAction -> result.add(modelMapper.map(sysMenuAction, SysMenuActionDto.class)));
        return result;
    }
}
