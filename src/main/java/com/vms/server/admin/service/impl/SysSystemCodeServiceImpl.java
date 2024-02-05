package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminSystemCodeDao;
import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.admin.service.SysSystemCodeService;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.dto.SystemCodeMappingDataDto;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SysSystemCodeServiceImpl implements SysSystemCodeService {

    private final AdminSysSystemCodeDataRepository sysSystemCodeDataRepository;
    private final AdminSystemCodeDao adminSystemCodeDao;
    private final ModelMapper modelMapper;

    @Override
    public List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, String orderBy, Boolean ascYn) {
        List<SysSystemCodeData> sysSystemCodeDataList = null;
        Sort sort = null;
        if(ascYn != null && orderBy != null) {
            if (ascYn) {
                sort = Sort.by(Sort.Direction.ASC, orderBy);
            } else {
                sort = Sort.by(Sort.Direction.DESC, orderBy);
            }
           sysSystemCodeDataList = sysSystemCodeDataRepository.findByPlantAndTableName(plant, tableName, sort);
        }else{
            sysSystemCodeDataList = sysSystemCodeDataRepository.findByPlantAndTableName(plant, tableName);
        }


        List<SysSystemCodeDataDto> sysSystemCodeDataDtos = new ArrayList<>();
        sysSystemCodeDataList.forEach(sysSystemCodeData -> sysSystemCodeDataDtos.add(modelMapper.map(sysSystemCodeData, SysSystemCodeDataDto.class)));
        return sysSystemCodeDataDtos;
    }

    @Override
    public List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView) {

        return adminSystemCodeDao.getSystemCodeList(plant,tableName,codeView);
    }

    @Override
    public List<SysSystemCodeDataDto> getSystemCodeList(String plant, String tableName, boolean codeView, String orderBy) {
        return adminSystemCodeDao.getSystemCodeList(plant,tableName,codeView,orderBy);
    }

    @Override
    public List<SystemCodeMappingDataDto> getSystemCodeMapList(String plant, String tableName, String orderBy, Boolean ascYn) {
        List<SysSystemCodeData> sysSystemCodeDataList = null;
        Sort sort = null;
        if(ascYn != null && orderBy != null) {
            if (ascYn) {
                sort = Sort.by(Sort.Direction.ASC, orderBy);
            } else {
                sort = Sort.by(Sort.Direction.DESC, orderBy);
            }
            sysSystemCodeDataList = sysSystemCodeDataRepository.findByPlantAndTableName(plant, tableName, sort);
        }else{
            sysSystemCodeDataList = sysSystemCodeDataRepository.findByPlantAndTableName(plant, tableName);
        }


        List<SystemCodeMappingDataDto> sysSystemCodeDataDtos = new ArrayList<>();
        sysSystemCodeDataList.forEach(sysSystemCodeData -> sysSystemCodeDataDtos.add(new SystemCodeMappingDataDto(sysSystemCodeData)));
        return sysSystemCodeDataDtos;
    }
}
