package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmAuthorityRoleDto;
import com.vms.server.domain.dto.AdmRoleGroupDto;
import com.vms.server.domain.vo.AdmAuthorityRoleVo;
import com.vms.server.domain.vo.AdmRoleGroupVo;

import java.util.List;

public interface AdminRoleManagerService {
    List<AdmRoleGroupVo> searchRoleMenuGroup(String plant, String searchOrder, String searchKeyword);

    void executeRoleMenuGroupDML(AdmRoleGroupDto dto) throws Exception;

    List<AdmAuthorityRoleVo> searchAuthorityRole(String plant, String searchOrder, String searchKeyword);


    List<AdmRoleGroupVo> searchOperGroupList(String plant);

    List<AdmRoleGroupVo> searchMenuGroupList(String plant);

    List<AdmAuthorityRoleVo> searchRoleGroupInfo(String plant, String roleId);

    List<AdmRoleGroupVo> makeRoleOperGroupSchema();

    List<AdmAuthorityRoleVo> makeRoleGroupSchema();

    void executeRoleGroupDML(AdmAuthorityRoleDto dto) throws Exception;
}
