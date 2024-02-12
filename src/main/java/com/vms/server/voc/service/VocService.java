package com.vms.server.voc.service;

import com.vms.server.admin.repository.jpa.AdminAdmPlantRepository;
import com.vms.server.domain.dto.ComboBoxItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VocService {
    private final AdminAdmPlantRepository adminAdmPlantRepository;

    @Transactional(readOnly = true)
    public List<ComboBoxItemDto> getComboBoxPlant() {
        return adminAdmPlantRepository.findByActivePlant("Y")
                .stream().map(admPlant ->
                    ComboBoxItemDto.builder()
                            .displayValue(admPlant.getPlant())
                            .value(admPlant.getPlant())
                            .description(admPlant.getDescription())
                            .build()
                ).collect(Collectors.toList());
    }
}
