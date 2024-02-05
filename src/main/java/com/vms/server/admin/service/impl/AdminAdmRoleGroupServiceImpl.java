package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmRoleGroupRepository;
import com.vms.server.admin.service.AdminAdmRoleGroupService;
import com.vms.server.domain.dto.AdmRoleGroupDto;
import com.vms.server.domain.entity.adm.AdmRoleGroup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAdmRoleGroupServiceImpl implements AdminAdmRoleGroupService {

    private final AdminAdmRoleGroupRepository adminAdmRoleGroupRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmRoleGroupDto> getAdmRoleGroup(AdmRoleGroupDto admRoleGroupDto) {
        List<AdmRoleGroupDto> result = new ArrayList<>();
        String plant = admRoleGroupDto.getPlant();
        String roleType = "M";
        List<AdmRoleGroup> admRoleGroupList = adminAdmRoleGroupRepository.findByPlantAndRoleTypeOrderByRoleGroup(plant, roleType);
        admRoleGroupList.forEach(admRoleGroup -> result.add(modelMapper.map(admRoleGroup, AdmRoleGroupDto.class)));

        return result;
    }
}
