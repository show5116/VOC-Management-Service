package com.vms.server.qms.repository.dao;

import com.vms.server.domain.dto.MrbManagerDto;
import com.vms.server.domain.dto.MrbSearchDto;
import com.vms.server.domain.dto.QmsApprovalRuleDto;

import java.util.List;

public interface QmsMrbDao {
    List<MrbSearchDto> getMrbSearch(MrbSearchDto dto);

    List<MrbManagerDto> getViewSelect(MrbManagerDto dto);

    List<QmsApprovalRuleDto> getApprovalList(String plant, String systemName, String qmsNumber, String revisionNo);
}
