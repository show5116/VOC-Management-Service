package com.vms.server.qms.service.impl;

import com.vms.server.domain.dto.LSSearchDto;
import com.vms.server.qms.repository.dao.LSSearchDao;
import com.vms.server.qms.service.LSSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LSSearchServiceImpl implements LSSearchService {

    private final LSSearchDao lsSearchDao;
    @Override
    public List<LSSearchDto> searchLsData(LSSearchDto dto) {
        return lsSearchDao.searchLsData(dto);
    }
}
