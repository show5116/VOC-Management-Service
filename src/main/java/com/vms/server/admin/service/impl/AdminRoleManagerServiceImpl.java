package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmAuthorityRoleRepository;
import com.vms.server.admin.repository.jpa.AdminAdmRoleGroupRepository;
import com.vms.server.admin.repository.querydsl.AdminAdmRoleManagerQueryRepository;
import com.vms.server.admin.service.AdminRoleManagerService;
import com.vms.server.domain.dto.AdmAuthorityRoleDto;
import com.vms.server.domain.dto.AdmRoleGroupDto;
import com.vms.server.domain.entity.adm.AdmAuthorityRole;
import com.vms.server.domain.entity.adm.AdmRoleGroup;
import com.vms.server.domain.vo.AdmAuthorityRoleVo;
import com.vms.server.domain.vo.AdmRoleGroupVo;
import com.vms.server.exception.CustomException;
import com.vms.server.exception.ErrorCode;
import com.vms.server.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AdminRoleManagerServiceImpl implements AdminRoleManagerService {

    private final AdminAdmRoleManagerQueryRepository adminAdmRoleGroupQueryRepository;
    private final AdminAdmRoleGroupRepository adminAdmRoleGroupRepository;
    private final AdminAdmAuthorityRoleRepository adminAdmAuthorityRoleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmRoleGroupVo> searchRoleMenuGroup(String plant, String searchOrder, String searchKeyword) {
        return adminAdmRoleGroupQueryRepository.searchRoleMenuGroup(plant, searchOrder, searchKeyword);
    }

    @Override
    @Transactional
    public void executeRoleMenuGroupDML(AdmRoleGroupDto dto) throws Exception{
        String dml = dto.getDml();
        String plant = dto.getPlant();
        String roleGroup = dto.getRoleGroup();
        String description = dto.getDescription();
        String roleType = dto.getRoleType();
        String regTime = DateUtil.getCurrentTimeFormatted();
        String regUser = dto.getRegUser();
        if(dml.equals("I")){
            AdmRoleGroup checkmenu = adminAdmRoleGroupRepository.findByPlantAndRoleGroup(plant,roleGroup);
            if(checkmenu != null){
                throw new CustomException(ErrorCode.DUPLICATE_ROLE_GROUP_ERROR);
            }
            adminAdmRoleGroupRepository.save(new AdmRoleGroup(plant,roleGroup,description,roleType,regTime,regUser));
        }else if(dml.equals("U")){
            adminAdmRoleGroupRepository.save(new AdmRoleGroup(plant,roleGroup,description,roleType,regTime,regUser));
        }else if(dml.equals("D")){
            adminAdmRoleGroupRepository.deleteByPlantAndRoleGroup(plant, roleGroup);
        }
    }

    @Override
    public List<AdmAuthorityRoleVo> searchAuthorityRole(String plant, String searchOrder, String searchKeyword) {
        return adminAdmRoleGroupQueryRepository.searchAuthorityRole(plant, searchOrder, searchKeyword);
    }

    @Override
    public List<AdmRoleGroupVo> searchOperGroupList(String plant) {
        List<AdmRoleGroupVo> result = new ArrayList<>();
        List<AdmRoleGroup> admRoleGroupList= adminAdmRoleGroupRepository.findByPlantAndRoleTypeOrderByRoleGroup(plant, "O");
        for(AdmRoleGroup admRoleGroup : admRoleGroupList){
            AdmRoleGroupVo admRoleGroupVo = new AdmRoleGroupVo();
            admRoleGroupVo.setDescription(admRoleGroup.getRoleGroup() + " : " +admRoleGroup.getDescription());
            admRoleGroupVo.setOperGroup(admRoleGroup.getRoleGroup());
            result.add(admRoleGroupVo);
        }
        return result;
    }

    @Override
    public List<AdmRoleGroupVo> searchMenuGroupList(String plant) {
        List<AdmRoleGroupVo> result = new ArrayList<>();
        List<AdmRoleGroup> admRoleGroupList= adminAdmRoleGroupRepository.findByPlantAndRoleTypeOrderByRoleGroup(plant, "M");
        for(AdmRoleGroup admRoleGroup : admRoleGroupList){
            AdmRoleGroupVo admRoleGroupVo = new AdmRoleGroupVo();
            admRoleGroupVo.setMenuGroup(admRoleGroup.getRoleGroup() + " : " +admRoleGroup.getDescription());
            admRoleGroupVo.setOperGroup(admRoleGroup.getRoleGroup());
            result.add(admRoleGroupVo);
        }
        return result;
    }

    @Override
    public List<AdmAuthorityRoleVo> searchRoleGroupInfo(String plant, String roleId) {
        List<AdmAuthorityRoleVo> result = new ArrayList<>();
        List<AdmAuthorityRole> admAuthorityRoleList = adminAdmAuthorityRoleRepository.findByPlantAndRoleId(plant,roleId);
        admAuthorityRoleList.forEach(admAuthorityRole -> result.add(modelMapper.map(admAuthorityRole, AdmAuthorityRoleVo.class)));
        return result;
    }

    @Override
    public List<AdmRoleGroupVo> makeRoleOperGroupSchema() {
        List<AdmRoleGroupVo> result = new ArrayList<>();
        List<AdmRoleGroup> admRoleGroupList= adminAdmRoleGroupRepository.findByPlant("BLANK");
        admRoleGroupList.forEach(admRoleGroup -> result.add(modelMapper.map(admRoleGroup,AdmRoleGroupVo.class )));
        return result;
    }

    @Override
    public List<AdmAuthorityRoleVo> makeRoleGroupSchema() {
        List<AdmAuthorityRoleVo> result = new ArrayList<>();
        List<AdmAuthorityRole> admAuthorityRoleList= adminAdmAuthorityRoleRepository.findByPlant("BLANK");
        admAuthorityRoleList.forEach(admAuthorityRole -> result.add(modelMapper.map(admAuthorityRole,AdmAuthorityRoleVo.class )));
        return result;
    }

    @Override
    @Transactional
    public void executeRoleGroupDML(AdmAuthorityRoleDto dto) throws Exception {
        String dml = dto.getDml();
        String plant = dto.getPlant();
        String roleId = dto.getRoleId();
        String description = dto.getDescription();
        String roleGrade = dto.getRoleGrade();
        String plantGroup = dto.getPlantGroup();
        String operationGroup = dto.getOperationGroup();
        String mesMenuGroup = dto.getMesMenuGroup();
        String reportMenuGroup = dto.getReportMenuGroup();
        String regTime = DateUtil.getCurrentTimeFormatted();
        String regUser = dto.getRegUser();
        String expandField1 = dto.getExpandField1();
        String expandField2 = dto.getExpandField2();
        String expandField3 = dto.getExpandField3();
        String expandField4 = dto.getExpandField4();
        String expandField5 = dto.getExpandField5();
        if(dml.equals("I") || dml.equals("U")){
            adminAdmAuthorityRoleRepository.save(new AdmAuthorityRole(plant,roleId,description,roleGrade,plantGroup,operationGroup,mesMenuGroup,reportMenuGroup,regTime,regUser,expandField1,expandField2,expandField3,expandField4,expandField5));
        }else if(dml.equals("D")){
            adminAdmAuthorityRoleRepository.deleteByPlantAndRoleId(plant, roleId);
        }
    }

}
