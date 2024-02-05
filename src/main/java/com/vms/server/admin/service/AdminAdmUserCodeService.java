package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmUserCodeAndDataDto;
import com.vms.server.domain.dto.AdmUserCodeDto;

import java.util.List;

public interface AdminAdmUserCodeService {

    List<AdmUserCodeDto> getAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto);

    void insertAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto);

    void updateAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto);

    void deleteAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto);
}
