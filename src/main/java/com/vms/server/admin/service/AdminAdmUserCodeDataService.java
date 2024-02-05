package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmUserCodeDataDto;
import com.vms.server.domain.dto.AdmUserCodeDto;

import java.util.List;

public interface AdminAdmUserCodeDataService {

    List<AdmUserCodeDataDto> getAdmUserCodeData(AdmUserCodeDto admUserCodeDto);
}
