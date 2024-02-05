package com.vms.server.qms.repository.querydsl;

import com.vms.server.domain.dto.QmsDocumentStatusDto;
import com.vms.server.domain.vo.SysSystemCodeDataVo;

import java.util.List;

public interface QmsKpiQueryRespository {

    List<SysSystemCodeDataVo> getRegYearList(String plant, String systemName);

    List<QmsDocumentStatusDto> getDocName(String plant, String titleSearch);
}
