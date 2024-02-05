package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminAdmPlantService;
import com.vms.server.domain.dto.AdmPlantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminAdmPlantController {

    private final AdminAdmPlantService adminAdmPlantService;

    @PostMapping("/admin/adm-plant")
    public ResponseEntity<List<AdmPlantDto>> getAdmPlant(@RequestBody AdmPlantDto admPlantDto) {
        String dml = admPlantDto.getDml();
        switch (dml) {
            /**
             * COMPANY 등록
             * @param admPlantDto(plant, description, numberOfShift, shift1Start, stdDaysPerWeek, stdHoursPerDay, activePlant)
             * 필수값 plant, description, numberOfShift, shift1Start, stdDaysPerWeek, stdHoursPerDay, activePlant
             */
            case "i":
                adminAdmPlantService.admPlantInsert(admPlantDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * COMPANY 삭제
             * @param admPlantDto(plant) 필수값 plant
             */
            case "d":
                adminAdmPlantService.admPlantDelete(admPlantDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * COMPANY
             * @param admPlantDto(plant, description) 선택값 plant or description
             */
            default:
                List<AdmPlantDto> admPlantList = adminAdmPlantService.getAdmPlant(admPlantDto);
                return ResponseEntity.ok(admPlantList);

        }
    }
}
