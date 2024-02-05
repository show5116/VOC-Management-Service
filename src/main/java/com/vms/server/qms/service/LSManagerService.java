package com.vms.server.qms.service;

import com.vms.server.domain.dto.LsActionDto;
import com.vms.server.domain.dto.QmsLsMasterDto;

import java.util.List;

public interface LSManagerService {
    void LSAction(LsActionDto dto);

    List<QmsLsMasterDto> getViewSelect(String plant, String systemName, String qmsNumber);

}
