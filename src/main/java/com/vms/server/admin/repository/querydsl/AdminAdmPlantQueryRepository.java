package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.dto.AdmPlantDto;
import com.vms.server.domain.entity.adm.AdmPlant;

import java.util.List;

public interface AdminAdmPlantQueryRepository {

    List<AdmPlant> getAdmPlant(AdmPlantDto admPlantDto);
}
