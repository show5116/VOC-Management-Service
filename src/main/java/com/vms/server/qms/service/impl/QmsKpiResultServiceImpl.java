package com.vms.server.qms.service.impl;

import com.vms.server.domain.dto.QmsKpiResultSearchDto;
import com.vms.server.domain.entity.qms.QmsKpiResultStatus;
import com.vms.server.domain.vo.AdmDeptVo;
import com.vms.server.domain.vo.QmsKpiResultStatusVo;
import com.vms.server.qms.repository.dao.QmsKpiResultDao;
import com.vms.server.qms.repository.jpa.QmsQmsKpiResultStatusRepository;
import com.vms.server.qms.repository.querydsl.QmsKpiResultQueryRepository;
import com.vms.server.qms.service.QmsKpiResultService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class QmsKpiResultServiceImpl implements QmsKpiResultService {

    private final ModelMapper modelMapper;
    private final QmsKpiResultQueryRepository qmsKpiResultQueryRepository;
    private final QmsQmsKpiResultStatusRepository qmsQmsKpiResultStatusRepository;
    private final QmsKpiResultDao qmsKpiResultDao;
    @Override
    public List<String> getDocNumber(String plant, String titleSearch) {
        return qmsKpiResultQueryRepository.getDocNumber(plant,titleSearch);
    }

    @Override
    public List<AdmDeptVo> getDept(String plant, String selectDept) {
        return qmsKpiResultDao.getDept(plant,selectDept);
    }

    @Override
    public List<Map<String, Object>> getSearch(QmsKpiResultSearchDto dto) {
        return qmsKpiResultDao.getSearch(dto);
    }

    @Override
    public List<QmsKpiResultStatusVo> getResultStatus(String plant, String systemName, String regYear, String kpiType, String deptBu, String functionType, String process, String subcontract) {
        List<QmsKpiResultStatusVo> result = new ArrayList<>();
        List<QmsKpiResultStatus> qmsKpiResultStatusList = null;
       if(kpiType.equals("INT")){
           qmsKpiResultStatusList = qmsQmsKpiResultStatusRepository.findByPlantAndSystemNameAndRegDateAndTypeAndDeptBuAndFunctionType(plant,systemName,regYear,kpiType,deptBu,functionType);
       }else{
           qmsKpiResultStatusList = qmsQmsKpiResultStatusRepository.findByPlantAndSystemNameAndRegDateAndTypeAndProcessAndSubcontract(plant,systemName,regYear,kpiType,process,subcontract);
       }

        qmsKpiResultStatusList.forEach(qmsKpiResultStatus -> result.add(modelMapper.map(qmsKpiResultStatus, QmsKpiResultStatusVo.class)));

        return result;
    }
}
