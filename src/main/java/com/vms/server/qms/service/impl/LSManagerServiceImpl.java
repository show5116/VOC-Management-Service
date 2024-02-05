package com.vms.server.qms.service.impl;

import com.vms.server.domain.dto.*;
import com.vms.server.domain.entity.qms.*;
import com.vms.server.qms.repository.dao.QMSCommonDao;
import com.vms.server.qms.repository.dao.LSManagerDao;
import com.vms.server.qms.repository.jpa.*;
import com.vms.server.qms.service.LSManagerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LSManagerServiceImpl implements LSManagerService {
    private final ModelMapper modelMapper;
    private final QmsLsMasterRepository qmsLsMasterRepository;
    private final QmsSelectedListRepository qmsSelectedListRepository;
    private final QmsLsDetailRepository qmsLsDetailRepository;
    private final QmsLsApplyRangeRepository qmsLsApplyRangeRepository;
    private final QmsAttacheFileRepository qmsAttacheFileRepository;
    private final QMSCommonDao commonDao;
    private final LSManagerDao lsManagerDao;
    @Override
    @Transactional
    public void LSAction(LsActionDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //QMS_LS_MASTER 테이블 INSERT 및 UPDATE
        QmsLsMaster qmsLsMaster = modelMapper.map(dto, QmsLsMaster.class);

        qmsLsMaster.updateRegDateAndUser( dto.getUser());
        if(qmsLsMaster.getClosedFlag().equals("Y")){
            qmsLsMaster.updateUpdateRegDateAndUser(dto.getUser());
        }
        qmsLsMasterRepository.save(qmsLsMaster);

        //QMS_SELECTED_LIST 삭제
        qmsSelectedListRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNoAndItemType(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(), dto.getRevisionNo(),"DEVICE");

        //QMS_SELECTED_LIST 입력
        List<String> productList = dto.getProductList();
        for(String product : productList){
            qmsSelectedListRepository.save(new QmsSelectedList(dto.getPlant(),dto.getSystemName(),dto.getQmsNumber(),"01", "DEVICE", product, product ));
        }

        //QMS_LS_DETAIL 삭제
        qmsLsDetailRepository.deleteByPlantAndSystemNameAndQmsNumberAndRevisionNo(dto.getPlant(), dto.getSystemName(), dto.getQmsNumber(), dto.getRevisionNo());

        //QMS_LS_APPLY_RANGE 삭제
        qmsLsApplyRangeRepository.deleteByPlantAndSystemNameAndQmsNumberAndRevisionNo(dto.getPlant(),dto.getSystemName(),dto.getQmsNumber(),dto.getRevisionNo());


        List<QmsLsDetailDto> qmsLsDetailDtoList = dto.getQmsLsDetailDtoList();
        if(qmsLsDetailDtoList != null && !qmsLsDetailDtoList.isEmpty()){
            for(int i = 0 ; i< qmsLsDetailDtoList.size(); i++){
                //QMS_LS_DETAIL 입력
                qmsLsDetailRepository.save(new QmsLsDetail(
                        dto.getPlant(),dto.getSystemName(),dto.getQmsNumber(),dto.getRevisionNo(),
                        (i+1)+"", qmsLsDetailDtoList.get(i).getCheckList(),
                        qmsLsDetailDtoList.get(i).getRemark(), qmsLsDetailDtoList.get(i).getKeyWord(),
                        qmsLsDetailDtoList.get(i).getFunctionType()));
                //QMS_LS_APPLY_RANGE 입력
                List<String> proceese = dto.getQmsLsDetailDtoList().get(i).getProcesses();
                for(int j = 0 ; j< proceese.size() ; j++){
                    qmsLsApplyRangeRepository.save(new QmsLsApplyRange(dto.getPlant(),dto.getSystemName(), dto.getQmsNumber(), dto.getRevisionNo(), (i+1)+"", proceese.get(j)));
                }
            }
        }
        //QMS_ATTACHED_FILE 삭제
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndSystemNameMtypeAndSystemNameStypeAndQmsNumberAndRevisionNo(dto.getPlant(), dto.getSystemName(),dto.getSysMType(),dto.getSysSType(),dto.getQmsNumber(),dto.getRevisionNo() );
        List<QmsAttachFileDto> qmsAttachFileDtoList = dto.getQmsAttachFileDtoList();
        if(qmsAttachFileDtoList != null && !qmsAttachFileDtoList.isEmpty()){
            //QMS_ATTACHED_FILE 입력
            for(int i = 0; i< qmsAttachFileDtoList.size(); i++){
             int attachedFileSeq = commonDao.getQmsAttachedFileSeq(dto.getPlant(),dto.getSystemName(),dto.getSysMType(),dto.getSysSType(),dto.getQmsNumber(),dto.getRevisionNo());
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(formatter);
            qmsAttacheFileRepository.save(new QmsAttachFile(dto.getPlant(),dto.getSystemName(),dto.getSysMType(),dto.getSysSType(), dto.getQmsNumber(),dto.getRevisionNo(),formattedDate, "", attachedFileSeq,qmsAttachFileDtoList.get(i).getFileName(), qmsAttachFileDtoList.get(i).getFileName(), qmsAttachFileDtoList.get(i).getFileDesc(), qmsAttachFileDtoList.get(i).getFilePath(),"" , "","","", "","" ));
            }
        }
    }

    @Override
    public List<QmsLsMasterDto> getViewSelect(String plant, String systemName, String qmsNumber) {
        return lsManagerDao.getViewSelect(plant,systemName,qmsNumber);
    }
}
