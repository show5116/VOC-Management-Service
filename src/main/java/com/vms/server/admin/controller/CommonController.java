package com.vms.server.admin.controller;

import com.vms.server.admin.service.CommonService;
import com.vms.server.domain.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;
    /**
     * user 기본정보 조회
     * @param dto(plant, userId)
     * @return
     */
    @PostMapping("/common/user/base-info")
    public ResponseEntity<CommonUserInfoDto> getUserBaseInfo (@RequestBody AdmUserInfoDto dto) {
        CommonUserInfoDto result = commonService.getUserBaseInfo(dto.getPlant(),dto.getUserId());
        return ResponseEntity.ok(result);
    }

    /**
     * user 기본정보 조회(multi)
     * @param dto(plant, userIds)
     * @return
     */
    @PostMapping("/common/user/base-info-multi")
    public ResponseEntity<List<CommonUserInfoDto>> getUserBaseInfoMulti (@RequestBody AdmUserInfoDto dto) {
        List<CommonUserInfoDto> result = commonService.getUserBaseInfoMulti(dto.getPlant(),dto.getUserIds());
        return ResponseEntity.ok(result);
    }

    /**
     * user 기본정보 조회(multi)
     * @param dto(plant, userNames)
     * @return
     */
    @PostMapping("/common/user/base-info-multi-name")
    public ResponseEntity<List<AdmUserInfoDto>> getUserBaseInfoMultiName (@RequestBody AdmUserInfoDto dto) {
        List<AdmUserInfoDto> result = commonService.getUserBaseInfoMultiName(dto.getPlant(),dto.getUserNames());
        return ResponseEntity.ok(result);
    }

    /**
     * 부서별 Dept Leader 조회
     * @param dto(deptId)
     * @return
     */
    @PostMapping("/common/dept-leader")
    public ResponseEntity<List<AdmDeptDto>> getDeptLeader (@RequestBody AdmDeptDto dto) {
        List<AdmDeptDto> result = commonService.getDeptLeader(dto.getDeptId());
        return ResponseEntity.ok(result);
    }

    /**
     * recipient list 조회
     * @param dto(plant, siteList)
     * @return
     */
    @PostMapping("/common/recipient-list")
    public ResponseEntity<List<SysSystemCodeDataDto>> getRecipientList (@RequestBody SysSystemCodeDataDto dto) {
        List<SysSystemCodeDataDto> result = commonService.getRecipientList(dto.getPlant(), dto.getSiteList());
        return ResponseEntity.ok(result);
    }

    /**
     * pgm List 조회
     * @param dto(plant, devices)
     * @return
     */
    @PostMapping("/common/pgm-list")
    public ResponseEntity<List<QmsPgmnameDataDto>> getPgmList (@RequestBody QmsPgmnameDataDto dto) {
        List<QmsPgmnameDataDto> result = commonService.getPgmList(dto.getPlant(), dto.getDevices());
        return ResponseEntity.ok(result);
    }

    /**
     * regist Info 조회
     * @param dto(plant, systemName, qmsNumber, revisionNo)
     * @return
     */
    @PostMapping("/common/regist-info")
    public ResponseEntity<List<AdmUserInfoDto>> getRegistInfo (@RequestBody QmsApprovalRuleDto dto) {
        List<AdmUserInfoDto> result = commonService.getRegistInfo(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(), dto.getRevisionNo());
        return ResponseEntity.ok(result);
    }

    /**
     * user Role Info 조회
     * @param dto(plant, systemName, qmsNumber, revisionNo, userId)
     * @return
     */
    @PostMapping("/common/user-roleinfo")
    public ResponseEntity<List<CommonRoleDto>> getUserRoleInfo (@RequestBody QmsApprovalRuleDto dto) {
        List<CommonRoleDto> result = commonService.getUserRoleInfo(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(), dto.getRevisionNo(), dto.getUserId());
        return ResponseEntity.ok(result);
    }

    /**
     * user Info 조회
     * @param dto(plant, systemName, qmsNumber, revisionNo, userId)
     * @return
     */
    @PostMapping("/common/user-info")
    public ResponseEntity<CommonUserInfoDto> getUserInfo (@RequestBody QmsApprovalRuleDto dto) {
        CommonUserInfoDto result = commonService.getUserInfo(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(), dto.getRevisionNo(), dto.getUserId());
        return ResponseEntity.ok(result);
    }

    /**
     * System Role 조회
     * @param dto(plant, systemName, role)
     * @return
     */
    @PostMapping("/common/role-info")
    public ResponseEntity<List<SystemCodeMappingDataDto>> getRoleInfo (@RequestBody QmsApprovalRuleDto dto) {
        List<SystemCodeMappingDataDto> result = commonService.getRoleInfo(dto.getPlant(), dto.getSystemName(), dto.getRole());
        return ResponseEntity.ok(result);
    }

    /**
     * QMS SELECTED LIST 조회
     * @param dto(plant, systemName, qmsNumber, revisionNo)
     * @return
     */
    @PostMapping("/common/product-select")
    public ResponseEntity<List<QmsSelectedListDto>> getProductSelect (@RequestBody QmsSelectedListDto dto) {
        List<QmsSelectedListDto> result = commonService.getProductSelect(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(),dto.getRevisionNo());
        return ResponseEntity.ok(result);
    }

    /**
     * QMS SELECTED LIST 조회
     * @param dto(plant, systemName, qmsNumber, revisionNo,itemType)
     * @return
     */
    @PostMapping("/common/product-select-list")
    public ResponseEntity<List<QmsSelectedListDto>> getProductSelectList (@RequestBody QmsSelectedListDto dto) {
        List<QmsSelectedListDto> result = commonService.getProductSelect(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(),dto.getRevisionNo());
        return ResponseEntity.ok(result);
    }

    /**
     * Customer 조회
     * @param dto(plant)
     * @return
     */
    @PostMapping("/common/customer-list")
    public ResponseEntity<List<CustomerMappingDto>> getCustomerList (@RequestBody AdmCustomerDto dto) {
        List<CustomerMappingDto> result = commonService.getCustomerList(dto.getPlant());
        return ResponseEntity.ok(result);
    }

    /**
     * Customer- supplier 조회
     * @param dto(plant)
     * @return
     */
    @PostMapping("/common/customer-supplier-list")
    public ResponseEntity<List<CustomerMappingDto>> getCustomerSupplierList (@RequestBody AdmCustomerDto dto) {
        List<CustomerMappingDto> result = commonService.getCustomerSupplierList(dto.getPlant());
        return ResponseEntity.ok(result);
    }

    /**
     * supplier 조회
     * @param dto(plant, processes)
     * @return
     */
    @PostMapping("/common/supplier-list")
    public ResponseEntity<List<SubcontractorMappingDto>> getCustomerSupplierList (@RequestBody SubcontractorMappingDto dto) {
        List<SubcontractorMappingDto> result = commonService.getSupplierList(dto.getPlant(),dto.getProcesses());
        return ResponseEntity.ok(result);
    }

    /**
     * supplier 조회
     * @param dto(plant, process)
     * @return
     */
    @PostMapping("/common/supplier-list/process")
    public ResponseEntity<List<SubcontractorMappingDto>> getCustomerSupplierListByProcess (@RequestBody SubcontractorMappingDto dto) {
        List<SubcontractorMappingDto> result = commonService.getSupplierList(dto.getPlant(),dto.getProcesses());
        return ResponseEntity.ok(result);
    }

    /**
     * SystemCode with Group1 조회
     * @param dto(plant, tableName, codeGroup1)
     * @return
     */
    @PostMapping("/common/systemcode-group1")
    public ResponseEntity<List<SystemCodeMappingDataDto>> getSystemCodeWitGroup1 (@RequestBody SysSystemCodeDataDto dto) {
        List<SystemCodeMappingDataDto> result = commonService.getRoleInfo(dto.getPlant(), dto.getTableName(), dto.getCodeGroup1());
        return ResponseEntity.ok(result);
    }

    /**
     * site 조회
     * @param dto(plant, facility)
     * @return
     */
    @PostMapping("/common/site")
    public ResponseEntity<List<SubcontractorMappingDto>> getSite (@RequestBody SubcontractorMappingDto dto) {
        List<SubcontractorMappingDto> result = commonService.getSite(dto.getPlant(), dto.getFacility());
        return ResponseEntity.ok(result);
    }

    /**
     * 부서 조회
     * @return
     */
    @PostMapping("/common/dept")
    public ResponseEntity<List<AdmDeptDto>> getDept () {
        List<AdmDeptDto> result = commonService.getDeptList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/common/column-size")
    public ResponseEntity<List<Integer>> getColumnsize(@RequestBody List<TableInfoDto> list) {
        List<Integer> result = commonService.getTextColumnSize(list);
        return ResponseEntity.ok(result);
    }




}
