package com.vms.server.voc.service;

import com.vms.server.admin.repository.jpa.AdminAdmPlantRepository;
import com.vms.server.admin.service.FileService;
import com.vms.server.domain.dto.ComboBoxItemDto;
import com.vms.server.domain.entity.qms.QmsVocHistory;
import com.vms.server.domain.entity.qms.QmsVocStatus;
import com.vms.server.domain.entity.qms.id.QmsVocStatusId;
import com.vms.server.voc.dto.request.VocSaveRequest;
import com.vms.server.voc.dto.response.VocListResponse;
import com.vms.server.voc.repository.QmsVocHistoryRepository;
import com.vms.server.voc.repository.QmsVocStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VocService {
    private final AdminAdmPlantRepository adminAdmPlantRepository;
    private final QmsVocStatusRepository qmsVocStatusRepository;
    private final QmsVocHistoryRepository qmsVocHistoryRepository;
    private final FileService fileService;

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

    @Transactional(readOnly = true)
    public List<VocListResponse> getVocList() {
        List<QmsVocStatus> qmsVocStatusList = qmsVocStatusRepository.findAll();
        return qmsVocStatusList.stream().map(VocListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveNewVoc(VocSaveRequest request) throws IOException {
        String qmsNumber = this.makeNewVocNumber();

        if (request.getFiles() != null && !request.getFiles().isEmpty()) {
            fileService.uploadFile(
                    request.makeFileRequest(qmsNumber));
        }

        QmsVocStatus vocStatus = QmsVocStatus.builder(new QmsVocStatusId(request.getPlant(), request.getSystemName(), qmsNumber, request.getRevisionNo()))
                .receptionDept(request.getReceptionDept())
                .requiredResponseDate(request.getRequiredResponseDate())
                .remark(request.getRemark())
                .personInCharge(request.getPersonInCharge())
                .requirement(request.getRequirement())
                .classification(request.getClassification())
                .build();

        QmsVocStatus savedVocStatus = qmsVocStatusRepository.saveAndFlush(vocStatus);
        QmsVocHistory vocHistory = savedVocStatus.toHistory();
        qmsVocHistoryRepository.save(vocHistory);
    }

    private String makeNewVocNumber() {
        String maxVocId = qmsVocStatusRepository.findMaxVocId();
        int maxVocNumber = 0;
        if (maxVocId != null) {
            maxVocNumber = Integer.parseInt(maxVocId.substring(3));
            maxVocNumber++;
        }

        return String.format("VOC%05d", maxVocNumber);
    }
}
