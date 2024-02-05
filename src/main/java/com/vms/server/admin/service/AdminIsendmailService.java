package com.vms.server.admin.service;

import com.vms.server.domain.dto.IsendmailDto;
import com.vms.server.domain.vo.IsendmailVo;

import java.util.List;

public interface AdminIsendmailService {

    List<IsendmailVo> getIsendmail(IsendmailDto isendmailDto);

    List<IsendmailDto> getSystem();
}
