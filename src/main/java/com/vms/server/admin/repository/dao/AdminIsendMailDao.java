package com.vms.server.admin.repository.dao;

import com.vms.server.domain.dto.IsendmailDto;

import java.util.List;

public interface AdminIsendMailDao {
    List<IsendmailDto> getIsendmail(IsendmailDto isendmailDto);
}
