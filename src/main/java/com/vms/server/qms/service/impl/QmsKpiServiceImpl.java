package com.vms.server.qms.service.impl;

import com.vms.server.admin.repository.jpa.AdminSysSystemCodeDataRepository;
import com.vms.server.domain.dto.*;
import com.vms.server.domain.entity.qms.QmsKpiList;
import com.vms.server.domain.entity.sys.SysSystemCodeData;
import com.vms.server.domain.vo.AdmDeptMappingVo;
import com.vms.server.domain.vo.KpiManagerVo;
import com.vms.server.domain.vo.SysSystemCodeDataVo;
import com.vms.server.qms.repository.dao.QmsKpiDao;
import com.vms.server.qms.repository.jpa.QmsQmsKpiListRepository;
import com.vms.server.qms.repository.querydsl.QmsKpiQueryRespository;
import com.vms.server.qms.service.QmsKpiService;
import com.vms.server.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QmsKpiServiceImpl implements QmsKpiService {

    private final ModelMapper modelMapper;
    private final QmsKpiQueryRespository qmsKpiQueryRespository;
    private final AdminSysSystemCodeDataRepository adminSysSystemCodeDataRepository;
    private final QmsKpiDao qmsKpiDao;
    private final QmsQmsKpiListRepository qmsQmsKpiListRepository;
    @Override
    public List<SysSystemCodeDataVo> getRegYearList(String plant, String systemName) {
        return qmsKpiQueryRespository.getRegYearList(plant,systemName);
    }

    @Override
    public List<SysSystemCodeDataVo> getKpiCycleList(String plant, String tableName) {
        List<SysSystemCodeDataVo> result = new ArrayList<>();
        Sort sort =  Sort.by(Sort.Direction.ASC, "codeSeq");
        List<SysSystemCodeData> sysSystemCodeDataList = adminSysSystemCodeDataRepository.findByPlantAndTableName(plant, tableName, sort);
        sysSystemCodeDataList.forEach(SysSystemCodeData -> result.add(modelMapper.map(SysSystemCodeData, SysSystemCodeDataVo.class)));
        return result;
    }

    @Override
    public List<QmsDocumentStatusDto> getDocName(String plant, String titleSearch) {

        return qmsKpiQueryRespository.getDocName(plant,titleSearch);
    }

    @Override
    public List<AdmDeptDto> getBu(String plant, String selectDept) {
        return qmsKpiDao.getBu(plant,selectDept);
    }

    @Override
    public List<SysSystemCodeDataDto> getfunctionType(String plant) {
        return qmsKpiDao.getfunctionType(plant);
    }

    @Override
    public List<SysSystemCodeDataDto> getRegUserList(String plant) {
        return qmsKpiDao.getRegUserList(plant);
    }

    @Override
    public List<QmsKpiSearchDto> getSearch(QmsKpiSearchDto dto) {
        return qmsKpiDao.getSearch(dto);
    }

    @Override
    public List<SysSystemCodeDataVo> getKpiCycleList(String plant) {
        List<SysSystemCodeDataVo> result = new ArrayList<>();
        Sort sort =  Sort.by(Sort.Direction.ASC, "codeSeq");
        List<SysSystemCodeData> sysSystemCodeDataList = adminSysSystemCodeDataRepository.findByPlantAndTableName(plant, "QMS_KPI_CYCLE", sort);
        sysSystemCodeDataList.forEach(SysSystemCodeData -> result.add(modelMapper.map(SysSystemCodeData, SysSystemCodeDataVo.class)));
        return result;
    }

    @Override
    public List<AdmDeptMappingVo> getDeptFunction(String plant, String deptId) {
        return qmsKpiDao.getDeptFunction(plant, deptId);
    }

    @Override
    public void kpiAction(KpiManagerDto dto) {
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String userId = dto.getUserId();
        String revision = dto.getRevision();
        String mode = dto.getMode();
        String deptBu = dto.getDeptBu();
        String deptBuName = dto.getDeptBuName();
        String functionType = dto.getFunctionType();
        String regYear = dto.getRegYear();
        String deleteKpi = dto.getDeleteKpi();
        String userName =dto.getUserName();
        String userDept = dto.getUserDept();
        boolean isReg = dto.isReg();
        List<KpiManagerVo> dtList = dto.getDtList();

        qmsKpiDao.kpiDeleteAction(plant,systemName,revision,deleteKpi,regYear,mode,deptBu,functionType);

        if(dtList != null && !dtList.isEmpty()){
            for(KpiManagerVo kpiManagerVo : dtList){
                String qmsNumber = kpiManagerVo.getQmsNumber();
                String regDate = DateUtil.getCurrentTimeFormatted();
                String regUser = userId;
                String updateDate = DateUtil.getCurrentTimeFormatted();
                String updateUser = userId;
                if(qmsNumber == null || qmsNumber.isEmpty()){
                    qmsNumber = qmsKpiDao.getQmsYear(plant,systemName,regYear.substring(2,2));
                    regDate = kpiManagerVo.getRegDate();
                    regUser = kpiManagerVo.getRegUser();
                }
                qmsQmsKpiListRepository.save(new QmsKpiList(plant,systemName, qmsNumber, revision, regYear, "",kpiManagerVo.getKpiNo(),kpiManagerVo.getDeptBu(),kpiManagerVo.getDeptId(), kpiManagerVo.getFunctionType(), kpiManagerVo.getProcess(), kpiManagerVo.getWeight(), regDate, regUser, updateDate, updateUser  ));
            }
        }

        //메일 전송

    }

    @Override
    public List<KpiManagerVo> getMailGrid(KpiManagerDto dto) {
        return null;
    }

    @Override
    public List<SysSystemCodeDataVo> setKpiType(SysSystemCodeDataDto dto) {
     List<SysSystemCodeDataVo> result = new ArrayList<>();
     List<SysSystemCodeData> systemCodeDataList= adminSysSystemCodeDataRepository.findByPlantAndTableNameAndCodeName(dto.getPlant(), dto.getTableName(), dto.getCodeName());
        systemCodeDataList.forEach(sysSystemCodeData -> result.add(modelMapper.map(sysSystemCodeData, SysSystemCodeDataVo.class)));
     return result;
    }

    @Override
    public List<KpiManagerVo> getKpiDel(KpiManagerDto dto) {
        return qmsKpiDao.getKpiDel(dto);
    }

    @Override
    public List<KpiManagerVo> getKpiMaster(KpiManagerDto dto) {
        return qmsKpiDao.getKpiMaster(dto);
    }

    @Override
    public List<KpiManagerVo> getRegKpiData(KpiManagerDto dto) {
        return qmsKpiDao.getRegKpiData(dto);
    }
}
