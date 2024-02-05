package com.vms.server.qms.controller;

import com.vms.server.domain.dto.CipSearchDto;
import com.vms.server.exception.CustomException;
import com.vms.server.qms.service.QmsCipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class CipController {

    private final QmsCipService qmsCpiSearchService;
    @PostMapping("/cip/search")
    public ResponseEntity<List<CipSearchDto>> cipSearchData (@RequestBody CipSearchDto dto)  throws CustomException {
        List<CipSearchDto> result = qmsCpiSearchService.getCipSearch(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cip/get-info")
    public ResponseEntity<CipSearchDto> cipGetInfo (@RequestBody CipSearchDto dto)  throws CustomException {
        CipSearchDto result = qmsCpiSearchService.getCipInfo(dto);
        return ResponseEntity.ok(result);
    }

}
