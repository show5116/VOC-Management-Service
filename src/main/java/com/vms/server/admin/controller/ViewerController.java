package com.vms.server.admin.controller;

import com.vms.server.admin.service.FileViewerService;
import com.vms.server.domain.dto.ViewerDto;
import com.vms.server.domain.vo.ViewerVo;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ViewerController {

    private final FileViewerService fileViewerService;
    @PostMapping("/viewer")
    public ResponseEntity<ViewerVo> fileViewer(@RequestBody ViewerDto viewerDto) throws Exception {
        String filePath = "C:/Users/Admin/Downloads/" + viewerDto.getPath();
        ViewerVo viewerVo = null;
        viewerVo  =fileViewerService.readFile(filePath);

        return ResponseEntity.ok().body(viewerVo);
    }
}
