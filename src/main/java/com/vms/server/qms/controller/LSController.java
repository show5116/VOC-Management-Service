package com.vms.server.qms.controller;

import com.vms.server.domain.dto.LSSearchDto;
import com.vms.server.domain.dto.LsActionDto;
import com.vms.server.exception.CustomException;
import com.vms.server.qms.service.LSManagerService;
import com.vms.server.qms.service.LSSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LSController {

    private final LSManagerService lsManagerService;
    private final LSSearchService lsSearchService;

    @PostMapping("/ls/lsaction")
    public void lsSearchData (@RequestBody LsActionDto dto)  throws CustomException {
          lsManagerService.LSAction(dto);
    }
    @PostMapping("/ls/search")
    public ResponseEntity<List<LSSearchDto>> lsSearchData (@RequestBody LSSearchDto dto)  throws CustomException {
        List<LSSearchDto> result = lsSearchService.searchLsData(dto);
        return ResponseEntity.ok(result);
    }

}
