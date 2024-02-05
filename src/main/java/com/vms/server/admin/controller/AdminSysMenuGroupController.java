package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminAdmPlantService;
import com.vms.server.admin.service.AdminAdmRoleGroupService;
import com.vms.server.admin.service.AdminSysMenuGroupService;
import com.vms.server.admin.service.AdminSysSystemCodeDataService;
import com.vms.server.domain.dto.AdmPlantDto;
import com.vms.server.domain.dto.AdmRoleGroupDto;
import com.vms.server.domain.dto.SysMenuGroupDto;
import com.vms.server.domain.dto.SysSystemCodeDataDto;
import com.vms.server.domain.vo.SysMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminSysMenuGroupController {

    private final AdminSysMenuGroupService adminSysMenuGroupService;
    private final AdminAdmPlantService adminAdmPlantService;
    private final AdminAdmRoleGroupService adminAdmRoleGroupService;
    private final AdminSysSystemCodeDataService adminSysSystemCodeDataService;

    @PostMapping("/admin/sys-menu-group")
    public ResponseEntity<List<SysMenuGroupDto>> getSysMenuGroup (@RequestBody SysMenuGroupDto sysMenuGroupDto) {
        String dml = sysMenuGroupDto.getDml();
        switch (dml) {
            /**
             * 메뉴 그룹 입력
             * @param sysMenuGroupDto(plant, groupId, moduleId) 필수값 plant, groupId, moduleId
             */
            case "i":
                adminSysMenuGroupService.insertSysMenuGroup(sysMenuGroupDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * 메뉴 그룹 삭제
             * @param sysMenuGroupDto(plant, groupId, moduleId) 필수값 plant, groupId, moduleId
             */
            case "d":
                adminSysMenuGroupService.deleteSysMenuGroup(sysMenuGroupDto);
                return new ResponseEntity(HttpStatus.OK);
            /**
             * Menu Group 조회
             * @param sysMenuGroupDto(plant, moduleId, groupId, groupName) 필수 plant, 선택1 modulId, 선택2 groupId or groupName
             */
            default:
                List<SysMenuGroupDto> sysMenuGroups = adminSysMenuGroupService.getSysMenuGroup(sysMenuGroupDto);
                return ResponseEntity.ok(sysMenuGroups);
        }
    }

    /**
     * 검색 조건 - 모듈
     * @param sysMenuGroupDto(plant)
     */
    @PostMapping("/admin/sys-menu-group/condition-module")
    public ResponseEntity<List<SysMenuGroupDto>> getConditionModule (@RequestBody SysMenuGroupDto sysMenuGroupDto) {
        List<SysMenuGroupDto> conditionModule = adminSysMenuGroupService.getConditionModule(sysMenuGroupDto);
        return ResponseEntity.ok(conditionModule);
    }

    /**
     * 등록 - 플랜트 조회
     */
    @PostMapping("/admin/sys-menu-group/plant")
    public ResponseEntity<List<AdmPlantDto>> getAdmPlant () {
        List<AdmPlantDto> admPlant = adminAdmPlantService.getAdmActivePlant();
        return ResponseEntity.ok(admPlant);
    }

    /**
     * 등록 - 메뉴 권한 그룹 조회
     * @param admRoleGroupDto(plant)
     */
    @PostMapping("/admin/sys-menu-group/role-group")
    public ResponseEntity<List<AdmRoleGroupDto>> getRoleGroup (@RequestBody AdmRoleGroupDto admRoleGroupDto) {
        List<AdmRoleGroupDto> admRoleGroup = adminAdmRoleGroupService.getAdmRoleGroup(admRoleGroupDto);
        return ResponseEntity.ok(admRoleGroup);
    }

    /**
     * 등록 - 모듈 조회
     * @param sysSystemCodeDataDto(plant)
     */
    @PostMapping("/admin/sys-menu-group/module")
    public ResponseEntity<List<SysSystemCodeDataDto>> getModule (@RequestBody SysSystemCodeDataDto sysSystemCodeDataDto) {
        List<SysSystemCodeDataDto> module = adminSysSystemCodeDataService.getModule(sysSystemCodeDataDto);
        return ResponseEntity.ok(module);
    }

    //TODO:사용자의 언어정보 필요
    /**
     * 메뉴 구조도 조회
     * @param sysMenuGroupDto(plant, groupId, moduleId)
     */
    @PostMapping("/admin/sys-menu-group/menu-tree")
    public ResponseEntity<List<SysMenuVo>> getMenuTree(@RequestBody SysMenuGroupDto sysMenuGroupDto) {
        List<SysMenuVo> menuTree = adminSysMenuGroupService.getMenuTree(sysMenuGroupDto);
        return ResponseEntity.ok(menuTree);
    }

}
