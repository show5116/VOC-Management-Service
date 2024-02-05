package com.vms.server.qms.repository.dao;

import com.vms.server.domain.dto.CipSearchDto;

import java.util.List;

public interface QMSCipDao {

List<CipSearchDto> getCpiSearch(CipSearchDto dto);

CipSearchDto getCpiInfo(CipSearchDto dto);

String getSystemCode(String plant, String itemType, String projectType);
}
