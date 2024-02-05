package com.vms.server.admin.repository.dao;

import com.vms.server.domain.dto.AdmUserInfoDto;

import java.util.List;

public interface AdminAdmUserInfoDao {
    List<AdmUserInfoDto> getUserInfoSysFileLog(AdmUserInfoDto admUserInfoDto);
}
