package com.vms.server.qms.service;

import com.vms.server.domain.dto.LSSearchDto;

import java.util.List;

public interface LSSearchService {

    List<LSSearchDto> searchLsData(LSSearchDto dto);

}
