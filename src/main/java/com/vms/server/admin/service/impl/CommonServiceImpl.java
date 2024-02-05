package com.vms.server.admin.service.impl;

import com.vms.server.admin.repository.dao.AdminAdmPlantDao;
import com.vms.server.admin.repository.dao.AdminCommonDao;
import com.vms.server.admin.repository.jpa.*;
import com.vms.server.admin.repository.querydsl.AdminAdmCustomerQueryRepository;
import com.vms.server.admin.repository.querydsl.AdminAdmSubcontractorQueryRepository;
import com.vms.server.admin.service.CommonService;
import com.vms.server.domain.dto.*;
import com.vms.server.domain.entity.adm.AdmCustomer;
import com.vms.server.domain.entity.adm.AdmDept;
import com.vms.server.domain.entity.adm.AdmEmployee;
import com.vms.server.domain.entity.adm.AdmSubcontractor;
import com.vms.server.domain.entity.qms.QmsPgmnameData;
import com.vms.server.domain.entity.qms.QmsSelectedList;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import com.vms.server.domain.vo.AdmPlantVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final ModelMapper modelMapper;
    private final AdminCommonDao commonDao;
    private final AdminAdmPlantDao adminAdmPlantDao;
    private final AdminSysSystemCodeDataRepository sysSystemCodeDataRepository;
    private final AdminQmsPgmnameDataRepository qmsPgmnameDataRepository;
    private final AdminQmsSelectedListRepository qmsSelectedListRepository;
    private final AdminAdmDeptRepository admDeptRepository;
    private final AdminAdmEmployeeRepository admEmployeeRepository;
    private final AdminAdmCustomerQueryRepository admCustomerQueryRepository;
    private final AdminAdmSubcontractorQueryRepository admSubcontractorQueryRepository;
    @Override
    public CommonUserInfoDto getUserBaseInfo(String plant, String userId) {
        return commonDao.getBaseUserInfo(plant,userId);
    }

    @Override
    public List<CommonUserInfoDto> getUserBaseInfoMulti(String plant, List<String> userIds) {
        return commonDao.getUserBaseInfoMulti(plant,userIds);
    }

    @Override
    public List<AdmUserInfoDto> getUserBaseInfoMultiName(String plant, List<String> userNames) {
        return commonDao.getUserBaseInfoMultiName(plant,userNames);
    }

    @Override
    public List<AdmDeptDto> getDeptLeader(String deptId) {
        return commonDao.getDeptLeader(deptId);
    }

    @Override
    public List<SysSystemCodeDataDto> getRecipientList(String plant, List<String> siteList) {
        List<SysSystemCodeDataDto> result = new ArrayList<>();
        List<SysSystemCodeData> sysSystemCodeDataList =  sysSystemCodeDataRepository.findByPlantAndTableNameAndSpecialData1In(plant,"QMS_SITE_MASTER",siteList);

        sysSystemCodeDataList.forEach(sysSystemCodeData -> result.add(modelMapper.map(sysSystemCodeData, SysSystemCodeDataDto.class)));
        return result;
    }

    @Override
    public List<QmsPgmnameDataDto> getPgmList(String plant, List<String> devices) {
    List<QmsPgmnameDataDto> result = new ArrayList<>();
    List<QmsPgmnameData> qmsPgmnameDataList = null;
    if(devices == null) {
        qmsPgmnameDataList =  qmsPgmnameDataRepository.findByPlant(plant);
    }else{
        qmsPgmnameDataList =  qmsPgmnameDataRepository.findByPlantAndDeviceIn(plant, devices);
    }
        qmsPgmnameDataList.forEach(qmsPgmnameData -> result.add(modelMapper.map(qmsPgmnameData, QmsPgmnameDataDto.class)));

        return result;
    }

    @Override
    public List<AdmUserInfoDto> getRegistInfo(String plant, String systemName, String qmsNumber, String revisionNo) {
        return commonDao.getRegistInfo(plant,systemName,qmsNumber,revisionNo);
    }

    @Override
    public List<CommonRoleDto> getUserRoleInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId) {

        return commonDao.getUserRoleInfo(plant,systemName,qmsNumber,revisionNo, userId);
    }

    @Override
    public CommonUserInfoDto getUserInfo(String plant, String systemName, String qmsNumber, String revisionNo, String userId) {
        return commonDao.getUserInfo(plant,systemName,qmsNumber,revisionNo, userId);
    }

    @Override
    public List<SystemCodeMappingDataDto> getRoleInfo(String plant, String systemName, String role) {
        List<SystemCodeMappingDataDto> result = new ArrayList<>();
        List<SysSystemCodeData> sysSystemCodeDataList =  sysSystemCodeDataRepository.findByPlantAndTableNameAndCodeGroup1(plant, systemName, role);

        sysSystemCodeDataList.forEach(sysSystemCodeData -> result.add(new SystemCodeMappingDataDto(sysSystemCodeData)));
        return result;
    }

    @Override
    public List<QmsSelectedListDto> getProductSelect(String plant, String systemName, String qmsNumber, String revisionNo) {
        List<QmsSelectedListDto> result = new ArrayList<>();
        List<QmsSelectedList> qmsSelectedListList = qmsSelectedListRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNoAndItemType(plant,systemName,qmsNumber,revisionNo,"DEVICE");
        qmsSelectedListList.forEach(qmsSelectedList -> result.add(modelMapper.map(qmsSelectedList, QmsSelectedListDto.class)));

        return result;
    }

    @Override
    public List<QmsSelectedListDto> getProductSelectList(String plant, String systemName, String qmsNumber, String revisionNo, String itemType) {
        List<QmsSelectedListDto> result = new ArrayList<>();
        List<QmsSelectedList> qmsSelectedListList = qmsSelectedListRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNoAndItemType(plant,systemName,qmsNumber,revisionNo,itemType);
        qmsSelectedListList.forEach(qmsSelectedList -> result.add(modelMapper.map(qmsSelectedList, QmsSelectedListDto.class)));

        return result;
    }

    @Override
    public String getUserMail(String userId) {
        String result = "";
        AdmEmployee admEmployee = admEmployeeRepository.findByUserId(userId);
        if(admEmployee != null){
            result = admEmployee.getEmpName()+"<"+admEmployee.getEmail()+">";
        }
        return result;
    }

    @Override
    public String getDeptMail(String deptId) {
       String result = "";
        AdmDept admDept = admDeptRepository.findByDeptId(deptId);
        if(admDept != null){
            result = admDept.getDeptName() + "<"+admDept.getDeptId()+"@siliconmitus.com"+">";
        }
        return result;
    }

    @Override
    public AdmUserInfoDto getDefaultUserInfo(String plant, String userId) {
        return commonDao.getDefaultUserInfo(plant,userId);
    }

    @Override
    public List<CustomerMappingDto> getCustomerList(String plant) {
        List<CustomerMappingDto> result = new ArrayList<>();
        List<AdmCustomer> admCustomerList = admCustomerQueryRepository.getCustomerList(plant);
        if(!admCustomerList.isEmpty()){
            admCustomerList.forEach(admCustomer -> result.add(modelMapper.map(admCustomer, CustomerMappingDto.class)));
        }

        return result;
    }

    @Override
    public List<CustomerMappingDto> getCustomerSupplierList(String plant) {
        return  commonDao.getCustomerSupplierList(plant);
    }

    @Override
    public List<SubcontractorMappingDto> getSupplierList(String plant, List<String> processes) {
        List<SubcontractorMappingDto> result = new ArrayList<>();
        List<AdmSubcontractor> admSubcontractorList = admSubcontractorQueryRepository.getSupplierList(plant, processes);
        if(!admSubcontractorList.isEmpty()){
            admSubcontractorList.forEach(admSubcontractor -> result.add(modelMapper.map(admSubcontractor, SubcontractorMappingDto.class)));
        }
        return result;
    }

    @Override
    public List<SubcontractorMappingDto> getSupplierList(String plant, String processes) {
        List<SubcontractorMappingDto> result = new ArrayList<>();
        List<AdmSubcontractor> admSubcontractorList = admSubcontractorQueryRepository.getSupplierList(plant,processes);
        if(!admSubcontractorList.isEmpty()){
            admSubcontractorList.forEach(admSubcontractor -> result.add(modelMapper.map(admSubcontractor, SubcontractorMappingDto.class)));
        }
        return result;
    }

    @Override
    public List<SubcontractorMappingDto> getSite(String plant, String facility) {
        List<SubcontractorMappingDto> result = new ArrayList<>();
        List<AdmSubcontractor> admSubcontractorList = admSubcontractorQueryRepository.getSite(plant, facility);
        if(!admSubcontractorList.isEmpty()){
            admSubcontractorList.forEach(admSubcontractor -> result.add(modelMapper.map(admSubcontractor, SubcontractorMappingDto.class)));
        }
        return result;
    }

    @Override
    public List<AdmDeptDto> getDeptList() {
        List<AdmDeptDto> result = new ArrayList<>();
        List<AdmDept> admDeptList = admDeptRepository.findAll();
        if(!admDeptList.isEmpty()){
            admDeptList.forEach(admDept -> result.add(modelMapper.map(admDept, AdmDeptDto.class)));
        }
        return result;
    }

    @Override
    public List<AdmPlantVo> searchPlant(String likeColumn, String likeKeyword, String activePlant) {
        return adminAdmPlantDao.searchPlant(likeColumn, likeKeyword, activePlant);
    }

    @Override
    public List<Integer> getTextColumnSize(List<TableInfoDto> list) {
        List<Integer> result = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            for(TableInfoDto tableInfoDto : list){
                result.add(commonDao.getTextColumnSize(tableInfoDto));
            }
        }
        return result;
    }
}
