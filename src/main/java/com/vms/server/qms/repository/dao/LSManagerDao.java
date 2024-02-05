package com.vms.server.qms.repository.dao;

import com.vms.server.domain.dto.QmsLsMasterDto;

import java.util.List;

public interface LSManagerDao {
    List<QmsLsMasterDto> getViewSelect(String plant, String systemName, String qmsNumber);

}
