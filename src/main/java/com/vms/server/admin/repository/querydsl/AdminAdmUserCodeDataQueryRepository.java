package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.dto.AdmUserCodeAndDataDto;
import com.vms.server.domain.entity.adm.AdmUserCode;

import java.util.List;

public interface AdminAdmUserCodeDataQueryRepository {
    List<AdmUserCode> getAdmUserCode(AdmUserCodeAndDataDto admUserCodeAndDataDto);

    void deleteByPlantAndTableName(String plant, String tableName);
}
