package com.vms.server.qms.service;

import com.vms.server.domain.dto.CipActionDto;
import com.vms.server.domain.dto.CipSearchDto;

import java.util.List;

public interface QmsCipService {

    List<CipSearchDto> getCipSearch(CipSearchDto dto);

    CipSearchDto getCipInfo(CipSearchDto dto);

    void cipAction(CipActionDto dto);

    void cpiDeleteAction(CipActionDto dto);

}
