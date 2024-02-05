package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.admin.repository.jpa.AdminSysSystemCodeRepository;
import com.vms.server.admin.repository.querydsl.AdminSysSystemCodeQueryRepository;
import com.vms.server.admin.service.AdminSysSystemCodeService;
import com.vms.server.domain.dto.SysSystemCodeAndDataDto;
import com.vms.server.domain.dto.SysSystemCodeDto;
import com.vms.server.domain.entity.sys.SysSystemCode;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminSysSystemCodeServiceImpl implements AdminSysSystemCodeService {

    private final AdminSysSystemCodeQueryRepository adminSysSystemCodeQueryRepository;
    private final AdminSysSystemCodeRepository adminSysSystemCodeRepository;
    private final AdminSysSystemCodeDataRepository adminSysSystemCodeDataRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<SysSystemCodeDto> getSysSystemCode(SysSystemCodeDto sysSystemCodeDto) {
        List<SysSystemCodeDto> result = new ArrayList<>();
        List<SysSystemCode> sysSystemCodeList = adminSysSystemCodeQueryRepository.getSysSystemCode(sysSystemCodeDto);
        sysSystemCodeList.forEach(sysSystemCode -> result.add(modelMapper.map(sysSystemCode, SysSystemCodeDto.class)));
        return result;
    }

    @Override
    public void insertSysSystemCode(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto) {
        if (!sysSystemCodeAndDataDto.isEmpty()) {
            SysSystemCode sysSystemCode = mapSysSystemCode(sysSystemCodeAndDataDto);
            adminSysSystemCodeRepository.save(sysSystemCode);

            List<SysSystemCodeData> sysSystemCodeDatas = new ArrayList<>();
            sysSystemCodeAndDataDto.forEach(dto -> {
                    SysSystemCodeData data = new SysSystemCodeData(
                            dto.getPlant(),
                            dto.getTableName(),
                            dto.getCodeName(),
                            dto.getCodeSeq(),
                            dto.getDataDescription(),
                            dto.getCodeGroup1(),
                            dto.getCodeGroup2(),
                            dto.getCodeGroup3(),
                            dto.getSpecialData1(),
                            dto.getSpecialData2(),
                            dto.getSpecialData3()
                    );
                    sysSystemCodeDatas.add(data);
                }
            );
            adminSysSystemCodeDataRepository.saveAll(sysSystemCodeDatas);
        }
    }

    private static SysSystemCode mapSysSystemCode(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto) {
        String plant = sysSystemCodeAndDataDto.get(0).getPlant();
        String tableName = sysSystemCodeAndDataDto.get(0).getTableName();
        String description = sysSystemCodeAndDataDto.get(0).getDescription();
        String formatType = sysSystemCodeAndDataDto.get(0).getFormatType();
        String decimalLength = sysSystemCodeAndDataDto.get(0).getDecimalLength();
        String sizeLimit = sysSystemCodeAndDataDto.get(0).getSizeLimit();

        return new SysSystemCode(
                plant,
                tableName,
                description,
                formatType,
                decimalLength,
                sizeLimit
        );
    }

    @Override
    public void updateSysSystemCode(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto) {
        if (!sysSystemCodeAndDataDto.isEmpty()) {
            SysSystemCode sysSystemCode = mapSysSystemCode(sysSystemCodeAndDataDto);
            adminSysSystemCodeRepository.save(sysSystemCode);
            adminSysSystemCodeDataRepository.deleteByPlantAndTableName(sysSystemCode.getPlant(), sysSystemCode.getTableName());
            
            List<SysSystemCodeData> sysSystemCodeDatas = new ArrayList<>();
            sysSystemCodeAndDataDto.forEach(dto -> {
                        SysSystemCodeData data = new SysSystemCodeData(
                                dto.getPlant(),
                                dto.getTableName(),
                                dto.getCodeName(),
                                dto.getCodeSeq(),
                                dto.getDataDescription(),
                                dto.getCodeGroup1(),
                                dto.getCodeGroup2(),
                                dto.getCodeGroup3(),
                                dto.getSpecialData1(),
                                dto.getSpecialData2(),
                                dto.getSpecialData3()
                        );
                        sysSystemCodeDatas.add(data);
                    }
            );
            adminSysSystemCodeDataRepository.saveAll(sysSystemCodeDatas);
        }
    }

    @Override
    public void deleteSysSystemCode(List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto) {
        if (!sysSystemCodeAndDataDto.isEmpty()) {
            SysSystemCode sysSystemCode = mapSysSystemCode(sysSystemCodeAndDataDto);
            adminSysSystemCodeRepository.delete(sysSystemCode);
            adminSysSystemCodeDataRepository.deleteByPlantAndTableName(sysSystemCode.getPlant(), sysSystemCode.getTableName());
        }
    }
}
