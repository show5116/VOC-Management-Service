package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.jpa.AdminAdmUserCodeDataRepository;
import com.vms.server.admin.repository.jpa.AdminAdmUserCodeRepository;
import com.vms.server.admin.repository.querydsl.AdminAdmUserCodeDataQueryRepository;
import com.vms.server.admin.service.AdminAdmUserCodeService;
import com.vms.server.domain.dto.AdmUserCodeAndDataDto;
import com.vms.server.domain.dto.AdmUserCodeDto;
import com.vms.server.domain.entity.adm.AdmUserCode;
import com.vms.server.domain.entity.adm.AdmUserCodeData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminAdmUserCodeServiceImpl implements AdminAdmUserCodeService {

    private final AdminAdmUserCodeDataQueryRepository adminAdmUserCodeDataQueryRepository;
    private final AdminAdmUserCodeRepository adminAdmUserCodeRepository;
    private final AdminAdmUserCodeDataRepository adminAdmUserCodeDataRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdmUserCodeDto> getAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto) {
        List<AdmUserCodeDto> result = new ArrayList<>();
        List<AdmUserCode> admUserCodeList = adminAdmUserCodeDataQueryRepository.getAdmUserCode(admUserCodeAndDataDto.get(0));
        admUserCodeList.forEach(userCode -> result.add(modelMapper.map(userCode, AdmUserCodeDto.class)));
        return result;
    }

    @Override
    public void insertAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto) {
        if (admUserCodeAndDataDto != null) {
            AdmUserCode admUserCode = mapUserCode(admUserCodeAndDataDto);
            adminAdmUserCodeRepository.save(admUserCode);

            List<AdmUserCodeData> admUserCodeDataList = new ArrayList<>();
            admUserCodeAndDataDto.forEach(dto -> {
                AdmUserCodeData admUserCodeData = new AdmUserCodeData(
                        dto.getPlant(),
                        dto.getTableName(),
                        dto.getCodeName(),
                        dto.getCodeSeq(),
                        dto.getDataDescription(),
                        dto.getCodeGroup1(),
                        dto.getCodeGroup2(),
                        dto.getCodeGroup3(),
                        admUserCode.getRegTime(),
                        dto.getRegUser()
                );
                admUserCodeDataList.add(admUserCodeData);
            });
            adminAdmUserCodeDataRepository.saveAll(admUserCodeDataList);
        }
    }

    private AdmUserCode mapUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto) {
        String plant = admUserCodeAndDataDto.get(0).getPlant();
        String tableName = admUserCodeAndDataDto.get(0).getTableName();
        String description = admUserCodeAndDataDto.get(0).getDescription();
        String tableType = admUserCodeAndDataDto.get(0).getTableType();
        String formatType = admUserCodeAndDataDto.get(0).getFormatType();
        String decimalLength = admUserCodeAndDataDto.get(0).getDecimalLength();
        String sizeLimit = admUserCodeAndDataDto.get(0).getSizeLimit();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String regTime = LocalDateTime.now().format(formatter);
        String regUser = admUserCodeAndDataDto.get(0).getRegUser();

        return new AdmUserCode(
                plant,
                tableName,
                description,
                tableType,
                formatType,
                decimalLength,
                sizeLimit,
                regTime,
                regUser
        );
    }

    @Override
    public void updateAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto) {
        if (!admUserCodeAndDataDto.isEmpty()) {
            AdmUserCode admUserCode = mapUserCode(admUserCodeAndDataDto);
            adminAdmUserCodeRepository.save(admUserCode);
            adminAdmUserCodeDataQueryRepository.deleteByPlantAndTableName(admUserCode.getPlant(), admUserCode.getTableName());

            List<AdmUserCodeData> admUserCodeDataList = new ArrayList<>();
            admUserCodeAndDataDto.forEach(dto -> {
                AdmUserCodeData admUserCodeData = new AdmUserCodeData(
                        dto.getPlant(),
                        dto.getTableName(),
                        dto.getCodeName(),
                        dto.getCodeSeq(),
                        dto.getDataDescription(),
                        dto.getCodeGroup1(),
                        dto.getCodeGroup2(),
                        dto.getCodeGroup3(),
                        admUserCode.getRegTime(),
                        dto.getRegUser()
                );
                admUserCodeDataList.add(admUserCodeData);
            });
            adminAdmUserCodeDataRepository.saveAll(admUserCodeDataList);
        }
    }

    @Override
    public void deleteAdmUserCode(List<AdmUserCodeAndDataDto> admUserCodeAndDataDto) {
        if (!admUserCodeAndDataDto.isEmpty()) {
            AdmUserCode admUserCode = mapUserCode(admUserCodeAndDataDto);
            adminAdmUserCodeRepository.delete(admUserCode);
            adminAdmUserCodeDataQueryRepository.deleteByPlantAndTableName(admUserCode.getPlant(), admUserCode.getTableName());
        }
    }
}
