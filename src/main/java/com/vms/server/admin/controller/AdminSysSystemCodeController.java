package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminSysSystemCodeDataService;
import com.vms.server.admin.service.AdminSysSystemCodeService;
import com.vms.server.domain.dto.SysSystemCodeAndDataDto;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.dto.SysSystemCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminSysSystemCodeController {

    private final AdminSysSystemCodeService adminSysSystemCodeService;
    private final AdminSysSystemCodeDataService adminSysSystemCodeDataService;

    /**
     * 시스템 코드 조회
     * @param sysSystemCodeDto(plant, tableName, description) 필수값 plant, 선택값 tableName, description
     */
    @PostMapping("/admin/sys-system-code")
    public ResponseEntity<List<SysSystemCodeDto>> getAdminSysSystemCode(@RequestBody SysSystemCodeDto sysSystemCodeDto) {
        List<SysSystemCodeDto> sysSystemCode = adminSysSystemCodeService.getSysSystemCode(sysSystemCodeDto);
        return ResponseEntity.ok(sysSystemCode);
    }

    @PostMapping("/admin/sys-system-code/code-data")
    public ResponseEntity<List<SysSystemCodeDataDto>> getAdminSystemCodeData(@RequestBody List<SysSystemCodeAndDataDto> sysSystemCodeAndDataDto) {
        String dml = sysSystemCodeAndDataDto.get(0).getDml();
        switch (dml) {
            /**
             * 시스템 코드&데이터 등록
             * @param sysSystemCodeAndDataDto(plant, tableName, description, formatType, decimalLength, sizeLimit, codeName, codeSeq, dataDescription)
             * 필수값 plant, tableName, formatType, decimalLength, sizeLimit, codeName, codeSeq
             */
            case "i":
                adminSysSystemCodeService.insertSysSystemCode(sysSystemCodeAndDataDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 시스템 코드&데이터 수정
             * @param sysSystemCodeAndDataDto(plant, tableName, description, formatType, decimalLength, sizeLimit, codeName, codeSeq, dataDescription)
             * 필수값 plant, tableName, formatType, decimalLength, sizeLimit, codeName, codeSeq
             */
            case "u":
                adminSysSystemCodeService.updateSysSystemCode(sysSystemCodeAndDataDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 시스템 코드&데이터 삭제
             * @param sysSystemCodeAndDataDto(plant, tableName) 필수값 plant, tableName
             */
            case "d":
                adminSysSystemCodeService.deleteSysSystemCode(sysSystemCodeAndDataDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 시스템 코드 데이터 조회
             * @param sysSystemCodeAndDataDto(plant, tableName) 필수값 plant, tableName
             */
            default:
                List<SysSystemCodeDataDto> sysSystemCodeData = adminSysSystemCodeDataService.getSysSystemCodeData(sysSystemCodeAndDataDto);
                return ResponseEntity.ok(sysSystemCodeData);
        }
    }
}
