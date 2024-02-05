package com.vms.server.qms.repository.dao;

import com.vms.server.domain.dto.LSSearchDto;

import java.util.List;

public interface LSSearchDao {

    List<LSSearchDto> searchLsData(LSSearchDto dto);
}
