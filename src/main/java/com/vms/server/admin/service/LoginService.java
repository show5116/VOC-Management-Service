package com.vms.server.admin.service;

import com.vms.server.domain.dto.CommonCodeDto;
import com.vms.server.domain.dto.LoginDto;
import com.vms.server.domain.dto.UserInfoDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface LoginService {

    UserInfoDto findByUserId(LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws Exception;

    List<CommonCodeDto> getPlantCode(String userId);
}
