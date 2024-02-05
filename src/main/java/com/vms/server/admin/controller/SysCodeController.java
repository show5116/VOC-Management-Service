package com.vms.server.admin.controller;


import com.vms.server.admin.service.SysSystemCodeService;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.dto.SystemCodeMappingDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SysCodeController {

    private final SysSystemCodeService sysSystemCodeService;

    @PostMapping("/syscode/system-code-list")
    public ResponseEntity<List<SysSystemCodeDataDto>> getSystemCodeList (@RequestBody SysSystemCodeDataDto dto) {
        List<SysSystemCodeDataDto> SysSystemCodeDataDtos = sysSystemCodeService.getSystemCodeList(dto.getPlant(), dto.getTableName(), dto.getOrderBy(),dto.isAscYn() );
        return ResponseEntity.ok(SysSystemCodeDataDtos);
    }
    @PostMapping("/syscode/system-code-list/code-view")
    public ResponseEntity<List<SysSystemCodeDataDto>> getSystemCodeListWithCodeView (@RequestBody SysSystemCodeDataDto dto) {
        List<SysSystemCodeDataDto> SysSystemCodeDataDtos = sysSystemCodeService.getSystemCodeList(dto.getPlant(), dto.getTableName(), dto.isCodeView());
        return ResponseEntity.ok(SysSystemCodeDataDtos);
    }

    @PostMapping("/syscode/system-code-list/code-view/orderby")
    public ResponseEntity<List<SysSystemCodeDataDto>> getSystemCodeListWithCodeViewOrderBy (@RequestBody SysSystemCodeDataDto dto) {
        List<SysSystemCodeDataDto> SysSystemCodeDataDtos = sysSystemCodeService.getSystemCodeList(dto.getPlant(), dto.getTableName(), dto.isCodeView(), dto.getOrderBy());
        return ResponseEntity.ok(SysSystemCodeDataDtos);
    }

    @PostMapping("/syscode/system-code-mapping-list")
    public ResponseEntity<List<SystemCodeMappingDataDto>> getSystemCodeMappingList (@RequestBody SysSystemCodeDataDto dto) {
        List<SystemCodeMappingDataDto> SysSystemCodeDataDtos = sysSystemCodeService.getSystemCodeMapList(dto.getPlant(), dto.getTableName(), dto.getOrderBy(),dto.isAscYn() );
        return ResponseEntity.ok(SysSystemCodeDataDtos);
    }
}
