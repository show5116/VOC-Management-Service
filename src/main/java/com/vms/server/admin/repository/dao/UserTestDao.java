package com.vms.server.admin.repository.dao;

import com.vms.server.domain.dto.AdmUserInfoDto;

import java.util.List;

public interface UserTestDao {

    List<AdmUserInfoDto> getUserInfoListById(String userId);
}
