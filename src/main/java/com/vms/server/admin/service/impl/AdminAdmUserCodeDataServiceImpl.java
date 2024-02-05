package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmUserCodeDataRepository;
import com.vms.server.admin.service.AdminAdmUserCodeDataService;
import com.vms.server.domain.dto.AdmUserCodeDataDto;
import com.vms.server.domain.dto.AdmUserCodeDto;
import com.vms.server.domain.entity.adm.AdmUserCodeData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAdmUserCodeDataServiceImpl implements AdminAdmUserCodeDataService {

    private final AdminAdmUserCodeDataRepository adminAdmUserCodeDataRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmUserCodeDataDto> getAdmUserCodeData(AdmUserCodeDto admUserCodeDto) {
        String plant = admUserCodeDto.getPlant();
        String tableName = admUserCodeDto.getTableName();

        List<AdmUserCodeDataDto> result = new ArrayList<>();
        List<AdmUserCodeData> admUserCodeDataList = adminAdmUserCodeDataRepository.findByPlantAndTableNameOrderByCodeSeqAsc(plant, tableName);
        admUserCodeDataList.forEach(admUserCodeData -> result.add(modelMapper.map(admUserCodeData, AdmUserCodeDataDto.class)));

        return result;
    }
}
