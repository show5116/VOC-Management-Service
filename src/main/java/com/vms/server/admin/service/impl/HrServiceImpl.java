package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmDeptRepository;
import com.vms.server.admin.service.HrService;
import com.vms.server.domain.dto.AdmDeptDto;
import com.vms.server.domain.entity.adm.AdmDept;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HrServiceImpl implements HrService {

    private final AdminAdmDeptRepository admDeptRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmDeptDto> getDept() {
        List<AdmDept> admDeptList = admDeptRepository.findAll();
        List<AdmDeptDto> result = new ArrayList<>();
        admDeptList.forEach(admDept -> result.add(modelMapper.map(admDept, AdmDeptDto.class)));
        return result;
    }

    @Override
    public List<AdmDeptMappingVo> getDeptByDeptName(String deptName) {
        List<AdmDeptMappingVo> result = new ArrayList<>();
        List<AdmDept> admDeptList  = admDeptRepository.findByDeptName(deptName);
        admDeptList.forEach(admDept -> result.add(modelMapper.map(admDept, AdmDeptMappingVo.class)));
        return result;
    }
}
