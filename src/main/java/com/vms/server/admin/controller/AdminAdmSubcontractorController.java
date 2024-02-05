package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminAdmSubcontractorService;
import com.vms.server.domain.dto.AdmSubcontractorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminAdmSubcontractorController {

    private final AdminAdmSubcontractorService adminAdmSubcontractorService;

    @PostMapping("/admin/adm-subcontractor")
    public ResponseEntity<List<AdmSubcontractorDto>> getAdmSubcontractor(@RequestBody AdmSubcontractorDto admSubcontractorDto) {
        String dml = admSubcontractorDto.getDml();

        switch (dml) {
            /**
             * 외주 정보 입력&수정
             * @param admSubcontractorDto(plant, subctrtCode) 필수값 plant, subctrtCode
             */
            case "i":
                adminAdmSubcontractorService.insertAdmSubcontractor(admSubcontractorDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 외주 정보 삭제
             * @param admSubcontractorDto(plant, subctrtCode) 필수값 plant, subctrtCode
             */
            case "d":
                adminAdmSubcontractorService.deleteAdmSubcontractor(admSubcontractorDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 외주 정보 조회
             * @param admSubcontractorDto(plant, subctrtDesc, subctrtCode) 필수값 plant, 선택값 subctrtDesc or subctrtCode
             */
            default:
                List<AdmSubcontractorDto> subcontractorList = adminAdmSubcontractorService.getAdmSubcontractor(admSubcontractorDto);
                return ResponseEntity.ok(subcontractorList);
        }
    }
}
