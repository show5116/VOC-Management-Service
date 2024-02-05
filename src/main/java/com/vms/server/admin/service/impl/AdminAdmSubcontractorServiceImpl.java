package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmSubcontractorRepository;
import com.vms.server.admin.repository.querydsl.AdminAdmSubcontractorQueryRepository;
import com.vms.server.admin.service.AdminAdmSubcontractorService;
import com.vms.server.domain.dto.AdmSubcontractorDto;
import com.vms.server.domain.entity.adm.AdmSubcontractor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAdmSubcontractorServiceImpl implements AdminAdmSubcontractorService {

    private final AdminAdmSubcontractorRepository adminAdmSubcontractorRepository;
    private final AdminAdmSubcontractorQueryRepository adminAdmSubcontractorQueryRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<AdmSubcontractorDto> getAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto) {
        List<AdmSubcontractorDto> result = new ArrayList<>();
        List<AdmSubcontractor> subcontractorList = adminAdmSubcontractorQueryRepository.getAdmSubcontractor(admSubcontractorDto);
        subcontractorList.forEach(subctrt-> result.add(modelMapper.map(subctrt, AdmSubcontractorDto.class)));
        return result;
    }

    @Override
    public void insertAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto) {
        if (admSubcontractorDto != null) {
            AdmSubcontractor admSubcontractor = modelMapper.map(admSubcontractorDto, AdmSubcontractor.class);
            adminAdmSubcontractorRepository.save(admSubcontractor);
        }
    }

    @Override
    public void deleteAdmSubcontractor(AdmSubcontractorDto admSubcontractorDto) {
        if (admSubcontractorDto != null) {
            AdmSubcontractor admSubcontractor = modelMapper.map(admSubcontractorDto, AdmSubcontractor.class);
            adminAdmSubcontractorRepository.delete(admSubcontractor);
        }
    }
}
