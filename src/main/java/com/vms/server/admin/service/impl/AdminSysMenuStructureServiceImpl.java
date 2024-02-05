package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminSysMenuGroupDao;
import com.vms.server.admin.repository.jpa.SysMenuStructureRepository;
import com.vms.server.admin.service.AdminSysMenuStructureService;
import com.vms.server.domain.dto.SysMenuStructureDto;
import com.vms.server.domain.entity.sys.SysMenuStructure;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminSysMenuStructureServiceImpl implements AdminSysMenuStructureService {

    private final SysMenuStructureRepository sysMenuStructureRepository;
    private final AdminSysMenuGroupDao adminSysMenuGroupDao;
    private final ModelMapper modelMapper;

    @Override
    public void insertSysMenuStructure(List<SysMenuStructureDto> sysMenuStructureDtos) {
        if (sysMenuStructureDtos != null) {
            sysMenuStructureRepository.deleteAllByPlantAndGroupIdAndModuleId(sysMenuStructureDtos.get(0).getPlant(), sysMenuStructureDtos.get(0).getGroupId(), sysMenuStructureDtos.get(0).getModuleId());

            List<SysMenuStructure> sysMenuStructure = new ArrayList<>();
            sysMenuStructureDtos.forEach(sysMenuStructureDto -> sysMenuStructure.add(modelMapper.map(sysMenuStructureDto, SysMenuStructure.class)));

            sysMenuStructureRepository.saveAll(sysMenuStructure);
        }
    }

    @Override
    public void deleteSysMenuStructure(List<SysMenuStructureDto> sysMenuStructureDtos) {
        if (sysMenuStructureDtos != null) {
            sysMenuStructureRepository.deleteAllByPlantAndGroupIdAndModuleId(sysMenuStructureDtos.get(0).getPlant(), sysMenuStructureDtos.get(0).getGroupId(), sysMenuStructureDtos.get(0).getModuleId());
        }
    }
}
