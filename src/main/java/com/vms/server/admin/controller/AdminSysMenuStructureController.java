package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminSysMenuGroupService;
import com.vms.server.admin.service.AdminSysMenuStructureService;
import com.vms.server.domain.dto.SysMenuGroupDto;
import com.vms.server.domain.dto.SysMenuStructureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminSysMenuStructureController {

    private final AdminSysMenuGroupService adminSysMenuGroupService;
    private final AdminSysMenuStructureService adminSysMenuStructureService;

    /**
     * MenuGroupId 조회
     * @param sysMenuGroupDto(plant, moduleId) 필수값 plant, moduleId
     */
    @PostMapping("/admin/sys-menu-structure/group-id")
    public ResponseEntity<List<SysMenuGroupDto>> getGroupId(@RequestBody SysMenuGroupDto sysMenuGroupDto) {
        List<SysMenuGroupDto> group = adminSysMenuGroupService.getGroupId(sysMenuGroupDto);
        return ResponseEntity.ok(group);
    }

    @PostMapping("/admin/sys-menu-structure")
    public ResponseEntity<HttpStatus> menuStructure(@RequestBody List<SysMenuStructureDto> sysMenuStructureDtos) {
        String dml = sysMenuStructureDtos.get(0).getDml();

        switch (dml) {
            case "i":
                adminSysMenuStructureService.insertSysMenuStructure(sysMenuStructureDtos);
                return new ResponseEntity(HttpStatus.OK);
            case "d":
                adminSysMenuStructureService.deleteSysMenuStructure(sysMenuStructureDtos);
                return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
