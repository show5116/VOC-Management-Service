package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.vo.AdmAuthorityRoleVo;
import com.vms.server.domain.vo.AdmRoleGroupVo;

import java.util.List;

public interface AdminAdmRoleManagerQueryRepository {
    List<AdmRoleGroupVo> searchRoleMenuGroup(String plant, String searchOrder, String searchKeyword);

    List<AdmAuthorityRoleVo> searchAuthorityRole(String plant, String searchOrder, String searchKeyword);
}
