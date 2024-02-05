package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminAdmUserInfoDao;
import com.vms.server.admin.repository.querydsl.AdminAdmUserInfoQueryRepository;
import com.vms.server.admin.service.AdminAdmUserInfoService;
import com.vms.server.domain.dto.AdmUserInfoDto;
import com.vms.server.domain.entity.adm.AdmUserInfo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAdmUserInfoServiceImpl implements AdminAdmUserInfoService {

    private final AdminAdmUserInfoQueryRepository adminAdmUserInfoQueryRepository;
    private final AdminAdmUserInfoDao adminAdmUserInfoDao;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmUserInfoDto> getUserInfo(AdmUserInfoDto admUserInfoDto) {
        List<AdmUserInfo> userInfoList = adminAdmUserInfoQueryRepository.getAdmUserInfo(admUserInfoDto);
        return userInfoList.stream().map(userInfo -> new AdmUserInfoDto(userInfo.userName, userInfo.userId)).collect(Collectors.toList());
    }

    @Override
    public List<AdmUserInfoDto> getUserInfoSysFileLog(AdmUserInfoDto admUserInfoDto) {
        return adminAdmUserInfoDao.getUserInfoSysFileLog(admUserInfoDto);
    }
}
