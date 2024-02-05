package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminAdmUserCodeDataService;
import com.vms.server.admin.service.AdminAdmUserCodeService;
import com.vms.server.domain.dto.AdmUserCodeAndDataDto;
import com.vms.server.domain.dto.AdmUserCodeDataDto;
import com.vms.server.domain.dto.AdmUserCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminAdmUserCodeController {

    private final AdminAdmUserCodeService adminUserCodeService;
    private final AdminAdmUserCodeDataService adminUserCodeDataService;

    @PostMapping("/admin/adm-user-code")
    public ResponseEntity<List<AdmUserCodeDto>> getAdmUserCode(@RequestBody List<AdmUserCodeAndDataDto> admUserCodeAndDataDto) {
        String dml = admUserCodeAndDataDto.get(0).getDml();
        switch (dml) {
            /**
             * 사용자 정의 코드&데이터 등록
             * @param admUserCodeAndDataDto(plant, tableName, tableType, formatType, decimalLength, sizeLimit, codeName, codeSeq)
             * 필수값 plant, tableName, tableType, formatType, decimalLength, sizeLimit, codeName, codeSeq
             */
            case "i":
                adminUserCodeService.insertAdmUserCode(admUserCodeAndDataDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 사용자 정의 코드&데이터 수정
             * @param admUserCodeAndDataDto(plant, tableName, tableType, formatType, decimalLength, sizeLimit, codeName, codeSeq)
             * 필수값 plant, tableName, tableType, formatType, decimalLength, sizeLimit, codeName, codeSeq
             */
            case "u":
                adminUserCodeService.updateAdmUserCode(admUserCodeAndDataDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 사용자 정의 코드&데이터 삭제
             * @param admUserCodeAndDataDto(plant, tableName)
             * 필수값 plant, tableName
             */
            case "d":
                adminUserCodeService.deleteAdmUserCode(admUserCodeAndDataDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 사용자 정의 코드 조회
             * @param admUserCodeAndDataDto(plant, tableName, description) 필수값 plant, 선택값 tableName or description
             */
            default:
                List<AdmUserCodeDto> admUserCodeAndData = adminUserCodeService.getAdmUserCode(admUserCodeAndDataDto);
                return ResponseEntity.ok(admUserCodeAndData);
        }
    }

    /**
     * 사용자 정의 코드 데이터 조회
     * @param admUserCodeDto(plant, tableName) 필수값 plant, tableName
     */
    @PostMapping("/admin/adm-user-code/code-data")
    public ResponseEntity<List<AdmUserCodeDataDto>> getAdmUserCodeData(@RequestBody AdmUserCodeDto admUserCodeDto) {
        List<AdmUserCodeDataDto> admUserCodeData = adminUserCodeDataService.getAdmUserCodeData(admUserCodeDto);
        return ResponseEntity.ok(admUserCodeData);
    }
}
