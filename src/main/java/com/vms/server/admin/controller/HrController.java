package com.vms.server.admin.controller;

import com.vms.server.admin.service.HrService;
import com.vms.server.domain.dto.AdmDeptDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HrController {

    private final HrService hrService;
    @PostMapping("/hr/get-dept")
    public ResponseEntity<List<AdmDeptDto>> getDept () {
        List<AdmDeptDto> result = hrService.getDept();
        return ResponseEntity.ok(result);

    }


}
