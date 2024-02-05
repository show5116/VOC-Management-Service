package com.vms.server.admin.repository.dao;

import com.vms.server.domain.dto.*;

import java.util.List;

public interface AdminCommonDao {

    CommonUserInfoDto getBaseUserInfo(String plant, String userId);

    List<CommonUserInfoDto> getUserBaseInfoMulti(String plant, List<String> userIds);

    List<AdmUserInfoDto> getUserBaseInfoMultiName(String plant, List<String> userNames);

    List<AdmDeptDto> getDeptLeader(String deptId);

    List<AdmUserInfoDto> getRegistInfo(String plant, String systemName, String qmsNumber, String revisionNo);

    List<CommonRoleDto> getUserRoleInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId);

    CommonUserInfoDto getUserInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId);

    AdmUserInfoDto getDefaultUserInfo(String plant, String userId);

    List<CustomerMappingDto> getCustomerSupplierList(String plant);

    Integer getTextColumnSize(TableInfoDto dto) ;
}
