package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminFunctionTypeMappingDao;
import com.vms.server.admin.repository.jpa.AdminAdmDeptMappingRepository;
import com.vms.server.admin.repository.jpa.AdminAdmDeptRepository;
import com.vms.server.admin.repository.jpa.AdminAdmSubcontractorRepository;
import com.vms.server.admin.service.AdminFunctionTypeMappingService;
import com.vms.server.domain.dto.AdmDeptDto;
import com.vms.server.domain.dto.AdmDeptMappingDto;
import com.vms.server.domain.entity.adm.AdmDeptMapping;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import com.vms.server.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminFunctionTypeMappingServiceImpl implements AdminFunctionTypeMappingService {

    private final AdminFunctionTypeMappingDao adminFunctionTypeMappingDao;
    private final AdminAdmDeptMappingRepository adminAdmDeptMappingRepository;
    public final AdminAdmDeptRepository admDeptRepository;
    public final AdminAdmSubcontractorRepository subcontractorRepository;;
    @Override
    public List<AdmDeptMappingVo> getFunctionTypeMappingData(String plant, String selectDept) {
        return adminFunctionTypeMappingDao.getFunctionTypeMappingData(plant,selectDept);
    }

    @Override
    public void UpdateDepartmentFunction(String plant, String regUser, List<AdmDeptMappingDto> deptMappingDtoList) {
         for(AdmDeptMappingDto admDeptMappingDto : deptMappingDtoList){
             adminAdmDeptMappingRepository.deleteByPlantAndDeptBuAndDeptId(plant, admDeptMappingDto.getDeptBu(), admDeptMappingDto.getDeptId());
        }

        for(AdmDeptMappingDto admDeptMappingDto : deptMappingDtoList){
            adminAdmDeptMappingRepository.save(new AdmDeptMapping(plant, admDeptMappingDto.getDeptBu(), admDeptMappingDto.getDeptId(), admDeptMappingDto.getFunctionType(), DateUtil.getCurrentTimeFormatted() ,regUser));
        }
    }

    @Override
    public List<AdmDeptDto> getUserDepartment() {

        List<AdmDeptDto> result = new ArrayList<>();
        AdmDeptDto startDept = new AdmDeptDto(admDeptRepository.findByUpDeptIdx("ROOT"));
        List<AdmDeptDto> deptList =  admDeptRepository.findAllByDeptStatus("U").stream().map(AdmDeptDto::new).collect(Collectors.toList());
        makeDepartTree(deptList, startDept);
        result.add(startDept);

        return result;
    }
    public void makeDepartTree (List<AdmDeptDto> deptList,AdmDeptDto admDeptDto){

        for(int i =0 ; i<deptList.size(); i++){
            if(deptList.get(i).getUpDeptIdx().equals(admDeptDto.getDeptId())){
                AdmDeptDto temp = deptList.get(i);
                admDeptDto.setChild(temp);
                makeDepartTree(deptList, temp);
            }
        }
    }
}
