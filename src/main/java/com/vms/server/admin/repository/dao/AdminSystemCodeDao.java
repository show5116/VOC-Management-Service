package com.vms.server.admin.repository.dao;


import com.vms.server.domain.dto.SysSystemCodeDataDto;

import java.util.List;

public interface AdminSystemCodeDao {
    List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView);

    List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView, String orderBy);
}
