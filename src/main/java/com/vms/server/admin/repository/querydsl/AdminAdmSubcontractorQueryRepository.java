package com.vms.server.admin.repository.querydsl;

import com.vms.server.domain.dto.AdmSubcontractorDto;
import com.vms.server.domain.entity.adm.AdmSubcontractor;

import java.util.List;

public interface AdminAdmSubcontractorQueryRepository {
    List<AdmSubcontractor> getSupplierList(String plant, List<String> processes);

    List<AdmSubcontractor> getSupplierList(String plant, String processes);

    List<AdmSubcontractor> getSite(String plant, String facility);

    List<AdmSubcontractor> getAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto);
}

