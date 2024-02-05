package com.vms.server.qms.repository.querydsl;

import com.vms.server.domain.dto.CommonUserInfoDto;

import java.util.List;

public interface QmsMrbQueryRepository {
    List<CommonUserInfoDto> selectedMemberList(String plant, String systemName, String qmsNumber, String revisionNo);

}
