package com.vms.server.admin.service;

import com.vms.server.domain.dto.AdmSubcontractorDto;

import java.util.List;

public interface AdminAdmSubcontractorService {

    List<AdmSubcontractorDto> getAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto);

    void insertAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto);

    void deleteAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto);
}
