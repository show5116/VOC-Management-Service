package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminSysMenuActionService;
import com.vms.server.domain.dto.SysMenuActionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminSysMenuActionController {

    private final AdminSysMenuActionService adminSysMenuActionService;

    @PostMapping("/admin/sys-menu-action")
    public ResponseEntity<List<SysMenuActionDto>> getSysMenuAction(@RequestBody SysMenuActionDto sysMenuActionDto) {
        String dml = sysMenuActionDto.getDml();
        switch (dml) {
            //TODO:사용자의 언어정보 필요
            /**
             * MenuAction 입력
             * @param sysMenuActionDto(moduleId) 필수 moduleId
             */
            case "i":
                adminSysMenuActionService.insertSysMenuAction(sysMenuActionDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * MenuAction 수정
             * @param sysMenuActionDto(actionSeq) 필수 actionSeq
             */
            case "u":
                adminSysMenuActionService.updateSysMenuAction(sysMenuActionDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * MenuAction 삭제
             * @param sysMenuActionDto(actionSeq) 필수 actionSeq
             */
            case "d":
                adminSysMenuActionService.deleteSysMenuAction(sysMenuActionDto);
                return new ResponseEntity(HttpStatus.OK);
            case "f":
                List<SysMenuActionDto> sysMenuActionDtos = adminSysMenuActionService.findSysMenuAction(sysMenuActionDto);
                return ResponseEntity.ok(sysMenuActionDtos);
            /**
             * MenuAction 조회
             * @param sysMenuActionDto(plant, moduleId, actionName, action) 필수 plant, 선택1 modulId, 선택2 actionName or action
             */
            default:
                List<SysMenuActionDto> sysMenuAction = adminSysMenuActionService.getSysMenuAction(sysMenuActionDto);
                return ResponseEntity.ok(sysMenuAction);
        }
    }
}
