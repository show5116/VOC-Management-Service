package com.vms.server.voc.controller;

import com.vms.server.domain.dto.ComboBoxItemDto;
import com.vms.server.voc.dto.request.VocRequest;
import com.vms.server.voc.dto.request.VocSaveRequest;
import com.vms.server.voc.dto.response.VocListResponse;
import com.vms.server.voc.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class VocController {
    private final VocService vocService;

    @GetMapping("/combo-box/system-name")
    public ResponseEntity<List<ComboBoxItemDto>> getComboBoxSystemName() {
        return ResponseEntity.ok(vocService.getComboBoxSystem());
    }

    @GetMapping("/combo-box/plant")
    public ResponseEntity<List<ComboBoxItemDto>> getComboBoxPlant() {
        return ResponseEntity.ok(vocService.getComboBoxPlant());
    }

    @GetMapping("/combo-box/person-in-charge")
    public ResponseEntity<List<ComboBoxItemDto>> getComboBoxPersonInCharge() {
        return ResponseEntity.ok(vocService.getComboPersonInCharge());
    }

    @GetMapping("/voc")
    public ResponseEntity<List<VocListResponse>> getVocList(@ModelAttribute VocRequest request) {
        return ResponseEntity.ok(vocService.getVocList(request));
    }

    @PostMapping("/voc")
    public ResponseEntity<Void> saveNewVoc(VocSaveRequest request) throws IOException {
        vocService.saveNewVoc(request);
        return ResponseEntity.ok().build();
    }
}
