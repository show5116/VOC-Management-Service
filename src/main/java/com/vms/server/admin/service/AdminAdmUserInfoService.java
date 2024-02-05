package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmUserInfoDto;

import java.util.List;

public interface AdminAdmUserInfoService {

    List<AdmUserInfoDto> getUserInfo(AdmUserInfoDto admUserInfoDto);

    List<AdmUserInfoDto> getUserInfoSysFileLog(AdmUserInfoDto admUserInfoDto);
}
