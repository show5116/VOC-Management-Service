package com.vms.server.admin.service;

import com.vms.server.domain.dto.*;
import com.vms.server.domain.vo.AdmPlantVo;

import java.util.List;

public interface CommonService {

    CommonUserInfoDto getUserBaseInfo(String plant, String userId);

    List<CommonUserInfoDto> getUserBaseInfoMulti(String plant, List<String> userIds);

    List<AdmUserInfoDto> getUserBaseInfoMultiName(String plant, List<String> userNames);

    List<AdmDeptDto> getDeptLeader(String deptId);

    List<SysSystemCodeDataDto> getRecipientList(String plant, List<String> siteList);

    List<QmsPgmnameDataDto> getPgmList(String plant, List<String> devices);

    List<AdmUserInfoDto> getRegistInfo(String plant, String systemName, String qmsNumber, String revisionNo);

    List<CommonRoleDto> getUserRoleInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId);

    CommonUserInfoDto getUserInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId);

    List<SystemCodeMappingDataDto> getRoleInfo(String plant, String systemName, String role);

    List<QmsSelectedListDto> getProductSelect(String plant, String systemName, String qmsNumber, String revisionNo);

    List<QmsSelectedListDto> getProductSelectList(String plant, String systemName, String qmsNumber, String revisionNo, String itemType);

    String getUserMail(String userCode);
    String getDeptMail(String deptCode);
    AdmUserInfoDto getDefaultUserInfo(String plant, String userId);

    List<CustomerMappingDto> getCustomerList(String plant);

    List<CustomerMappingDto> getCustomerSupplierList(String plant);

    List<SubcontractorMappingDto> getSupplierList(String plant, List<String> processes);

    List<SubcontractorMappingDto> getSupplierList(String plant, String processes);

    List<SubcontractorMappingDto> getSite(String plant, String facility);

    List<AdmDeptDto> getDeptList();

    List<AdmPlantVo> searchPlant(String likeColumn, String likeKeyword, String activePlant);

    List<Integer> getTextColumnSize(List<TableInfoDto> dto);


}

