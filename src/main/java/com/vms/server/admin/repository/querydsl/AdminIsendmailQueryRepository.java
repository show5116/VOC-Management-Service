package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.dto.IsendmailDto;

import java.util.List;

public interface AdminIsendmailQueryRepository {

    List<IsendmailDto> getSystem();
}
