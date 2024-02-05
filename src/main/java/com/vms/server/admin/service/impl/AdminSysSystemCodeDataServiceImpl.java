package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.admin.service.AdminSysSystemCodeDataService;
import com.vms.server.domain.dto.SysSystemCodeAndDataDto;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminSysSystemCodeDataServiceImpl implements AdminSysSystemCodeDataService {

    private final AdminSysSystemCodeDataRepository adminSysSystemCodeDataRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<SysSystemCodeDataDto>
    getModule(SysSystemCodeDataDto sysSystemCodeDataDto) {
        String plant = sysSystemCodeDataDto.getPlant();
        String tableName = "APP_TYPE";
        String codeName = "COMMON";

        List<SysSystemCodeData> moduleList = adminSysSystemCodeDataRepository.findByPlantAndTableNameAndCodeNameNotOrderByCodeSeq(plant, tableName, codeName);
        return moduleList.stream().map(module -> new SysSystemCodeDataDto(
                    module.codeName,
                    module.description
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<SysSystemCodeDataDto> getSysSystemCodeData(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto) {
        String plant = sysSystemCodeAndDataDto.get(0).getPlant();
        String tableName = sysSystemCodeAndDataDto.get(0).getTableName();
        List<SysSystemCodeData> sysSystemCodeDataList = adminSysSystemCodeDataRepository.findByPlantAndTableNameOrderByCodeSeqAsc(plant, tableName);

        return sysSystemCodeDataList.stream().map(sysSystemCodeData -> new SysSystemCodeDataDto(
                sysSystemCodeData.plant,
                sysSystemCodeData.tableName,
                sysSystemCodeData.codeName,
                sysSystemCodeData.codeSeq,
                sysSystemCodeData.description,
                sysSystemCodeData.codeGroup1,
                sysSystemCodeData.codeGroup2,
                sysSystemCodeData.codeGroup3,
                sysSystemCodeData.specialData1,
                sysSystemCodeData.specialData2,
                sysSystemCodeData.specialData3
        )).collect(Collectors.toList());
    }

    @Override
    public List<SysSystemCodeDataDto> getApplicationCd(String plant, String tableName) {
        List<SysSystemCodeDataDto> result = new ArrayList<>();
        List<SysSystemCodeData> list = adminSysSystemCodeDataRepository.findByPlantAndTableName(plant, tableName);
        list.forEach(data -> result.add(modelMapper.map(data, SysSystemCodeDataDto.class)));
        return result;
    }
}
