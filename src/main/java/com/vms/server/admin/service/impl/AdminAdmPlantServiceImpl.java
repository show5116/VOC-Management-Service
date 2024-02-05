package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmPlantRepository;
import com.vms.server.admin.repository.querydsl.AdminAdmPlantQueryRepository;
import com.vms.server.admin.service.AdminAdmPlantService;
import com.vms.server.domain.dto.AdmPlantDto;
import com.vms.server.domain.entity.adm.AdmPlant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAdmPlantServiceImpl implements AdminAdmPlantService {

    private final AdminAdmPlantQueryRepository adminAdmPlantQueryRepository;
    private final AdminAdmPlantRepository adminAdmPlantRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmPlantDto> getAdmPlant(AdmPlantDto admPlantDto) {
        List<AdmPlantDto> result = new ArrayList<>();
        List<AdmPlant> admPlantList = adminAdmPlantQueryRepository.getAdmPlant(admPlantDto);
        admPlantList.forEach(admPlant -> result.add(modelMapper.map(admPlant, AdmPlantDto.class)));
        return result;
    }

    @Override
    public List<AdmPlantDto> getAdmActivePlant() {
        String isActive = "Y";
        List<AdmPlantDto> result = new ArrayList<>();
        List<AdmPlant> admPlantList = adminAdmPlantRepository.findByActivePlant(isActive);
        admPlantList.forEach(admPlant -> result.add(modelMapper.map(admPlant, AdmPlantDto.class)));
        return result;
    }

    @Override
    public void admPlantInsert(AdmPlantDto admPlantDto) {
        if (admPlantDto != null) {
            AdmPlant admPlant = modelMapper.map(admPlantDto, AdmPlant.class);
            adminAdmPlantRepository.save(admPlant);
        }
    }

    @Override
    public void admPlantDelete(AdmPlantDto admPlantDto) {
        if (admPlantDto != null) {
            AdmPlant admPlant = modelMapper.map(admPlantDto, AdmPlant.class);
            adminAdmPlantRepository.delete(admPlant);
        }
    }
}
