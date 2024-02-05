package com.vms.server.admin.service;

import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.dto.SystemCodeMappingDataDto;

import java.util.List;

public interface SysSystemCodeService {

    List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, String orderBy, Boolean ascYn);

    List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView);
    List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView, String orderBy);

    List<SystemCodeMappingDataDto> getSystemCodeMapList(String plant, String tableName, String orderBy, Boolean ascYn);
}
