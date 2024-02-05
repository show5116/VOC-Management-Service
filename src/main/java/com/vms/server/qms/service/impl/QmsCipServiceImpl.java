package com.vms.server.qms.service.impl;

import com.vms.server.domain.dto.CipActionDto;
import com.vms.server.domain.dto.CipSearchDto;
import com.vms.server.domain.dto.QmsAttachFileDto;
import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.QmsCipStatus;
import com.vms.server.domain.entity.qms.QmsSelectedList;
import com.vms.server.qms.repository.dao.QMSCipDao;
import com.vms.server.qms.repository.dao.QMSCommonDao;
import com.vms.server.qms.repository.jpa.QmsAttacheFileRepository;
import com.vms.server.qms.repository.jpa.QmsQmsCipStatusRepository;
import com.vms.server.qms.repository.jpa.QmsSelectedListRepository;
import com.vms.server.qms.service.QmsCipService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QmsCipServiceImpl implements QmsCipService {
    private final ModelMapper modelMapper;
    private final QMSCipDao qmsCipDao;
    private final QMSCommonDao qmsCommonDao;
    private final QmsQmsCipStatusRepository qmsQmsCipStatusRepository;
    private final QmsSelectedListRepository qmsSelectedListRepository;
    private final QmsAttacheFileRepository qmsAttacheFileRepository;

    @Override
    public List<CipSearchDto> getCipSearch(CipSearchDto dto) {
        return qmsCipDao.getCpiSearch(dto);
    }

    @Override
    public CipSearchDto getCipInfo(CipSearchDto dto) {
        return qmsCipDao.getCpiInfo(dto);
    }

    @Override
    public void cipAction(CipActionDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String sysMType = dto.getSysMType();
        String sysSType = dto.getSysSType();
        String dml = dto.getDml();
        String qmsNumber = dto.getQmsNumber();
        String revisionNo = dto.getRevisionNo();
        String projectTitle = dto.getProjectTitle();
        List<String> projectTypes = dto.getProjectTypes();
        String process = dto.getProcess();
        String improvementItems = dto.getImprovementItems();
        String improvementResult=dto.getImprovementResult();
        String currentLevel= dto.getCurrentLevel();
        String targetLevel = dto.getTargetLevel();
        String closedFlag = dto.getClosedFlag();
        String completeDate = dto.getCompleteDate();
        String issueDate = dto.getIssueDate();
        String estimatedCmplDate = dto.getEstimatedCmplDate();
        String userId = dto.getUserid();

        QmsCipStatus updateYn= qmsQmsCipStatusRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant,systemName,qmsNumber,revisionNo);
        QmsCipStatus qmsCipStatus = modelMapper.map(dto, QmsCipStatus.class);
        if(updateYn != null){
            qmsCipStatus.updateUpdateRegDateAndUser(userId);
        }else{
            qmsCipStatus.updateRegDateAndUser(userId);
        }
        qmsQmsCipStatusRepository.save(qmsCipStatus);

        qmsSelectedListRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNoAndItemType(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(), dto.getRevisionNo(),"QMS_PROJECT_TYPE");

        for(String projectType : projectTypes){
            String itemCode =  qmsCipDao.getSystemCode(plant, "QMS_PROJECT_TYPE", projectType);
            qmsSelectedListRepository.save(new QmsSelectedList(plant,systemName,qmsNumber,revisionNo, "QMS_PROJECT_TYPE" ,itemCode, projectType ));
        }

        //QMS_ATTACHED_FILE 삭제
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(plant, systemName,sysMType,sysSType,qmsNumber,revisionNo );
        List<QmsAttachFileDto> qmsAttachFileDtoList = dto.getQmsAttachFileDtoList();
        if(qmsAttachFileDtoList != null && !qmsAttachFileDtoList.isEmpty()){
            //QMS_ATTACHED_FILE 입력
            for(int i = 0; i< qmsAttachFileDtoList.size(); i++){
                    int attachedFileSeq = qmsCommonDao.getQmsAttachedFileSeq(plant,systemName,sysMType,sysSType,qmsNumber,revisionNo);
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(formatter);
                qmsAttacheFileRepository.save(new QmsAttachFile(plant,systemName,sysMType,sysSType, qmsNumber,revisionNo,formattedDate, "", attachedFileSeq,qmsAttachFileDtoList.get(i).getFileName(), qmsAttachFileDtoList.get(i).getFileName(), qmsAttachFileDtoList.get(i).getFileDesc(), qmsAttachFileDtoList.get(i).getFilePath(),"" , "","","", "","" ));
            }
        }
    }

    @Override
    public void cpiDeleteAction(CipActionDto dto) {
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String sysMType = dto.getSysMType();
        String sysSType = dto.getSysSType();
        String dml = dto.getDml();
        String qmsNumber = dto.getQmsNumber();
        String revisionNo = dto.getRevisionNo();
        List<String> projectTypes = dto.getProjectTypes();
        String userId = dto.getUserid();

        qmsQmsCipStatusRepository.deleteByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant,systemName, qmsNumber, revisionNo);
        qmsSelectedListRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNoAndItemType(plant,systemName,qmsNumber,revisionNo,"QMS_PROJECT_TYPE");
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(plant,systemName,sysMType,sysSType,qmsNumber,revisionNo);

    }
}
