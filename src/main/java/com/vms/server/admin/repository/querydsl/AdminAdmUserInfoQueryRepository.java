package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.dto.AdmUserInfoDto;
import com.vms.server.domain.entity.adm.AdmUserInfo;

import java.util.List;

public interface AdminAdmUserInfoQueryRepository {
    List<AdmUserInfo> getAdmUserInfo(AdmUserInfoDto admUserInfoDto);
}
