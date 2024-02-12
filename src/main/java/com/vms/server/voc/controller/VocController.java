package com.vms.server.voc.controller;

import com.vms.server.domain.dto.ComboBoxItemDto;
import com.vms.server.voc.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VocController {
    private final VocService vocService;

    @GetMapping("/combo-box/plant")
    public ResponseEntity<List<ComboBoxItemDto>> getComboBoxPlant() {
        return ResponseEntity.ok(vocService.getComboBoxPlant());
    }
}
