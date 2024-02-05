package com.vms.server.admin.controller;

import com.vms.server.admin.service.AdminRoleManagerService;
import com.vms.server.admin.service.CommonService;
import com.vms.server.domain.dto.AdmPlantSearchDto;
import com.vms.server.domain.dto.AdmRoleGroupDto;
import com.vms.server.domain.dto.AdmRoleMenuSearchDto;
import com.vms.server.domain.vo.AdmAuthorityRoleVo;
import com.vms.server.domain.vo.AdmPlantVo;
import com.vms.server.domain.vo.AdmRoleGroupVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminRoleManagerController {

    private final AdminRoleManagerService adminRoleManagerService;
    private final CommonService commonService;

    @PostMapping("/role/plant-info")
    public ResponseEntity<List<AdmPlantVo>> getSystemCodeList (@RequestBody AdmPlantSearchDto dto) {
        List<AdmPlantVo> result = commonService.searchPlant(dto.getLikeColumn(), dto.getLikeKeyword(), dto.getActivePlant());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/role/role-menu-group")
    public ResponseEntity<List<AdmRoleGroupVo>> searchRoleMenuGroup (@RequestBody AdmRoleMenuSearchDto dto) {
        List<AdmRoleGroupVo> result = adminRoleManagerService.searchRoleMenuGroup(dto.getPlant(), dto.getSearchOrder(), dto.getLikeKeyword());
        return ResponseEntity.ok(result);
    }
    @PostMapping("/role/role-menu-group/action")
    public void executeRoleMenuGroupDML (@RequestBody AdmRoleGroupDto dto) throws Exception{
        adminRoleManagerService.executeRoleMenuGroupDML(dto);
    }

    @PostMapping("/role/authority-role")
    public ResponseEntity<List<AdmAuthorityRoleVo>> searchAuthorityRole (@RequestBody AdmRoleMenuSearchDto dto) {
        List<AdmAuthorityRoleVo> result = adminRoleManagerService.searchAuthorityRole(dto.getPlant(), dto.getSearchOrder(), dto.getLikeKeyword());
        return ResponseEntity.ok(result);
    }

}
