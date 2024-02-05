package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmPlantDto;

import java.util.List;

public interface AdminAdmPlantService {
    List<AdmPlantDto> getAdmPlant(AdmPlantDto admPlantDto);

    List<AdmPlantDto> getAdmActivePlant();

    void admPlantInsert(AdmPlantDto admPlantDto);

    void admPlantDelete(AdmPlantDto admPlantDto);
}
