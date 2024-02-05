package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminFunctionTypeMappingService;
import com.vms.server.domain.dto.AdmDeptDto;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminFunctionTypeMappingController {

    private final AdminFunctionTypeMappingService adminFunctionTypeMappingService;

    @PostMapping("/menu/dept/mapping-data")
    public ResponseEntity<List<AdmDeptMappingVo>> getFunctionTypeMappingData(@RequestBody AdmDeptDto dto){
        List<AdmDeptMappingVo> result = adminFunctionTypeMappingService.getFunctionTypeMappingData(dto.getPlant(), dto.getDeptId());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/menu/find/user-department")
    public ResponseEntity<List<AdmDeptDto>> getUserDepartment(){
        List<AdmDeptDto> result = adminFunctionTypeMappingService.getUserDepartment();

        return ResponseEntity.ok(result);
    }
}
