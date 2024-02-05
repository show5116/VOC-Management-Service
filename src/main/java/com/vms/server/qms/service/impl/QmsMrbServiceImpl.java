package com.vms.server.qms.service.impl;

import com.vms.server.admin.service.FileService;
import com.vms.server.domain.dto.*;
import com.vms.server.domain.entity.qms.*;
import com.vms.server.domain.entity.sys.SysChangeDrafterLog;
import com.vms.server.qms.repository.dao.QMSCommonDao;
import com.vms.server.qms.repository.dao.QmsMrbDao;
import com.vms.server.qms.repository.jpa.*;
import com.vms.server.qms.repository.querydsl.QmsMrbQueryRepository;
import com.vms.server.qms.repository.querydsl.QmsQmsAttachedFileQueryRepository;
import com.vms.server.qms.service.QmsMrbService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QmsMrbServiceImpl implements QmsMrbService {

    private final QmsMrbDao qmsMrbDao;
    private final QMSCommonDao qmsCommonDao;
    private final QmsQmsMrbLotListRepository qmsQmsMrbLotListRepository;
    private final QmsQmsActionItemListRepository qmsQmsActionItemListRepository;
    private final QmsQmsMrbMasterRepository qmsQmsMrbMasterRepository;
    private final QmsAttacheFileRepository qmsAttacheFileRepository;
    private final QmsQmsApprovalRuleRepository qmsQmsApprovalRuleRepository;
    private final QmsQmsAttachedFileQueryRepository qmsQmsAttachedFileQueryRepository;
    private final QmsSelectedListRepository qmsSelectedListRepository;
    private final QmsSysChangeDrafterLogRepository qmsSysChangeDrafterLogRepository;
    private final QmsMrbQueryRepository qmsMrbQueryRepository;
    private final FileService fileService;
    private final ModelMapper modelMapper;

    @Override
    public List<MrbSearchDto> getMrbSearch(MrbSearchDto dto) {
        return qmsMrbDao.getMrbSearch(dto);
    }

    @Override
    public List<QmsMrbLotListDto> getLoadNonConf(String plant, String systemName, String qmsNumber) {
        List<QmsMrbLotListDto> result = new ArrayList<>();
        List<QmsMrbLotList> qmsMrbLotLists = qmsQmsMrbLotListRepository.findByPlantAndSystemNameAndQmsNumber(plant, systemName, qmsNumber);
        qmsMrbLotLists.forEach(qmsMrbLotList -> result.add(modelMapper.map(qmsMrbLotList, QmsMrbLotListDto.class)));


        return result;
    }

    @Override
    public List<QmsActionItemListDto> getLoadActionItem(String plant, String systemName, String qmsNumber) {

        List<QmsActionItemListDto> result = new ArrayList<>();
        List<QmsActionItemList> qmsActionItemLists = qmsQmsActionItemListRepository.findByPlantAndSystemNameAndQmsNumberOrderBySeqNo(plant, systemName, qmsNumber);
        qmsActionItemLists.forEach(qmsActionItemList -> result.add(modelMapper.map(qmsActionItemList, QmsActionItemListDto.class)));
        for (QmsActionItemListDto qmsActionItemListDto : result) {
            qmsActionItemListDto.changeDateFormat();
        }
        return result;
    }

    @Override
    public List<QmsAttachFileDto> getLoadAttachedFile(String plant, String systemName, String qmsNumber, String systemNameType) {
        List<QmsAttachFileDto> result = qmsQmsAttachedFileQueryRepository.getLoadAttachedFile(plant, systemName, qmsNumber, systemNameType);

        return result;
    }

    @Override
    public List<CommonUserInfoDto> selectedMemberList(String plant, String systemName, String qmsNumber, String revisionNo) {
        return qmsMrbQueryRepository.selectedMemberList(plant, systemName, qmsNumber, revisionNo);
    }

    @Override
    public List<QmsApprovalRuleDto> getApprovalList(String plant, String systemName, String qmsNumber, String revisionNo) {
        return qmsMrbDao.getApprovalList(plant, systemName, qmsNumber, revisionNo);
    }

    @Override
    @Transactional
    public void mrbClose(MrbManagerDto dto) throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        String plant = dto.getPlant();
        String userId = dto.getUserId();
        String systemName = dto.getSystemName();
        String mrbNumber = dto.getMrbNumber();
        String closedDate = dto.getClosedDate();
        boolean close = dto.isClose();
        boolean drop = dto.isDrop();
        List<QmsAttachFileDto> customerReportList = dto.getCustomerReportList();
        List<QmsAttachFileDto> validationList = dto.getValidationList();


        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, dto.getQmsNumber(), "01");
        qmsMrbMaster.updateClosedDateAndUser(closedDate, userId);
        qmsMrbMaster.changeCurrentStep("70");
        if (close) {
            qmsMrbMaster.changeClosedFlag("Y");
        } else if (drop) {
            qmsMrbMaster.changeClosedFlag("D");
        } else {
            qmsMrbMaster.changeClosedFlag("N");
        }
        qmsQmsMrbMasterRepository.save(qmsMrbMaster);


        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndSystemNameMtypeAndRevisionNo(plant, systemName, mrbNumber, "SUPPLIER", "01");

        if (customerReportList != null && !customerReportList.isEmpty()) {
            //QMS_ATTACHED_FILE 입력
            for (int i = 0; i < customerReportList.size(); i++) {
                fileService.saveAttachFile(customerReportList.get(i).getMultipartFile(), plant, mrbNumber, "systemName","SUPPLIER",  i + "", "" , customerReportList.get(i).getFileDesc());
           }
        }


        //QMS_ATTACHED_FILE 삭제
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndSystemNameMtypeAndRevisionNo(plant, systemName, mrbNumber, "VALIDATION", "01");


        if (validationList != null && !validationList.isEmpty()) {
            //QMS_ATTACHED_FILE 입력
            for (int i = 0; i < validationList.size(); i++) {
                int attachedFileSeq = qmsCommonDao.getQmsAttachedFileSeq(plant, systemName, "SUPPLIER", i + "", mrbNumber, "01");
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(formatter);
                qmsAttacheFileRepository.save(new QmsAttachFile(plant, systemName, "SUPPLIER", i + "", mrbNumber, "", formattedDate, "", attachedFileSeq, validationList.get(i).getFileName(), validationList.get(i).getFileName(), validationList.get(i).getFileDesc(), validationList.get(i).getFilePath(), "", formattedDate, "", "", "", ""));
            }
        }
    }

    @Override
    public void mrbReject(MrbManagerDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String mrbNumber = dto.getMrbNumber();
        String regUser = dto.getRegUser();
        String comment = dto.getComment();
        String today = dto.getToday();
        String revisionNo = "01";
        String userId = dto.getUserId();
        String title = dto.getTitle();
        int approvalRow = dto.getApprovalRow();
        List<QmsApprovalRuleDto> approvalList = dto.getApprovalList();
        boolean isMailError = false;

        //QMS_MRB_MASTER 정보 수정
        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, mrbNumber, "01");
        qmsMrbMaster.changeCurrentStep("55");
        qmsQmsMrbMasterRepository.save(qmsMrbMaster);

        //QMS_APPROVAL_RULE 삭제
        qmsQmsApprovalRuleRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, mrbNumber, revisionNo);


        for (int i = 0; i < approvalList.size(); i++) {
            String approvalDate = "";
            String approvalFlag = "";
            String comments = "";

            if (i == approvalRow) {
                approvalDate = today;
                approvalFlag = "R";
                comments = comment;
            } else {
                approvalDate = approvalList.get(i).getDate();
                approvalFlag = approvalList.get(i).getStatusR();
                comments = approvalList.get(i).getComment();
            }

            qmsQmsApprovalRuleRepository.save(new QmsApprovalRule(plant, systemName, mrbNumber, revisionNo, approvalList.get(i).getRoleId(), approvalDate, approvalFlag, userId, comments));
        }

        //반려 메일 전송

    }

    @Override
    public void mrbEntrust(MrbManagerDto dto) {
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String txtMrbNumber = dto.getMrbNumber();
        String regUser = dto.getRegUser();
        String comment = dto.getComment();
        String today = dto.getToday();
        String revisionNo = "01";
        String userId = dto.getUserId();
        String title = dto.getTitle();
        int approvalRow = dto.getApprovalRow();
        List<QmsApprovalRuleDto> approvalList = dto.getApprovalList();
        boolean isMailError = false;

        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, txtMrbNumber, "01");
        qmsMrbMaster.changeCurrentStep("60");
        qmsQmsMrbMasterRepository.save(qmsMrbMaster);


        //QMS_APPROVAL_RULE 삭제
        qmsQmsApprovalRuleRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, txtMrbNumber, revisionNo);

        for (int i = 0; i < approvalList.size(); i++) {
            String approvalDate = "";
            String approvalFlag = "";
            String comments = "";
            if (i == approvalRow) {
                approvalDate = today;
                if (approvalList.get(i).getStatusR().equals("E")) {
                    approvalFlag = "E";
                } else {
                    approvalFlag = "Y";
                }
                comments = comment;
            } else {
                approvalDate = approvalList.get(i).getDate();
                approvalFlag = approvalList.get(i).getStatusR();
                comments = approvalList.get(i).getComment();
            }
            qmsQmsApprovalRuleRepository.save(new QmsApprovalRule(plant, systemName, txtMrbNumber, revisionNo, approvalList.get(i).getRoleId(), approvalDate, approvalFlag, userId, comments));
        }

        //메일 전송

    }

    @Override
    public void mrbApproval(MrbManagerDto dto) {
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String txtMrbNumber = dto.getMrbNumber();
        String regUser = dto.getRegUser();
        String comment = dto.getComment();
        String today = dto.getToday();
        String revisionNo = "01";
        String userId = dto.getUserId();
        String title = dto.getTitle();
        int approvalRow = dto.getApprovalRow();
        List<QmsApprovalRuleDto> approvalList = dto.getApprovalList();
        List<CommonUserInfoDto> memberList = dto.getMeberList();
        boolean isMailError = false;
        boolean closedFlag = true;
        if (approvalList != null) {
            if (approvalList.size() <= 0) {
                closedFlag = false;
            }

            for (int i = 0; i < approvalList.size(); i++) {
                if ((approvalList.get(i).getStatusR().equals("N") && i != approvalRow) || approvalList.get(i).getStatusR().equals("R")) {
                    closedFlag = false;
                }
            }
        } else {
            closedFlag = false;
        }
        //QMS_MRB_MASTER 정보 수정
        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, txtMrbNumber, "01");

        if (closedFlag) {
            qmsMrbMaster.changeCurrentStep("70");
        } else {
            qmsMrbMaster.changeCurrentStep("60");
        }
        qmsQmsMrbMasterRepository.save(qmsMrbMaster);

        //QMS_APPROVAL_RULE 삭제
        qmsQmsApprovalRuleRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, txtMrbNumber, revisionNo);


        for (int i = 0; i < approvalList.size(); i++) {
            String approvalDate = "";
            String approvalFlag = "";
            String comments = "";
            if (i == approvalRow) {
                approvalDate = today;
                if (approvalList.get(i).getStatusR().equals("E")) {
                    approvalFlag = "E";
                } else {
                    approvalFlag = "Y";
                }
                comments = comment;
            } else {
                approvalDate = approvalList.get(i).getDate();
                approvalFlag = approvalList.get(i).getStatusR();
                comments = approvalList.get(i).getComment();
            }
            qmsQmsApprovalRuleRepository.save(new QmsApprovalRule(plant, systemName, txtMrbNumber, revisionNo, approvalList.get(i).getRoleId(), approvalDate, approvalFlag, userId, comments));
        }


        //메일 전송

    }

    @Override
    public void mrbMerge(MrbManagerDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String mrbNumber = dto.getMrbNumber();
        String revisionNo = "01";
        String title = dto.getTitle();
        String issueDate = dto.getIssueDate();
        String process = dto.getProcess();
        String site = dto.getSite();
        String problemDesc = dto.getProblemDesc();
        String causeDesc = dto.getCauseDesc();
        String problem = dto.getProblem();
        String cause = dto.getCause();
        boolean external = dto.isExternal();
        boolean validation = dto.isValidation();
        String regDate = dto.getRegDate();
        String regUser = dto.getRegUser();
        String lotDisposition = dto.getLotDisposition();
        String reason = dto.getReason();
        boolean insert = dto.isInsert();
        String stepFlag = dto.getStepFlag();
        String changeStep = dto.getChangeStep();

        List<QmsMrbLotListDto> nonConfList = dto.getNonConfList();
        List<QmsActionItemListDto> actionItemList = dto.getActionItemList();
        List<QmsApprovalRuleDto> approvalList = dto.getApprovalList();
        List<CommonUserInfoDto> memberList = dto.getMeberList();
        List<QmsAttachFileDto> reportList = dto.getReportList();
        List<QmsAttachFileDto> customerReportList = null;
        List<QmsAttachFileDto> validationList = null;
        if (stepFlag.equals("999")) {
            customerReportList = dto.getCustomerReportList();
            validationList = dto.getValidationList();
        }

        String comment = dto.getComment();
        String mailFlag = dto.getMailFlag();
        String closedDate = dto.getClosedDate();
        String closedUser = dto.getClosedUser();
        boolean close = dto.isClose();
        boolean drop = dto.isDrop();

        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, mrbNumber, revisionNo);
        if (qmsMrbMaster != null) {
            String closeFlag = "";
            String closeDate = "";
            String closeUser = "";
            String currentStep = "00";
            if (stepFlag.equals("999") && (closedDate != null && !closedDate.isEmpty())) {
                if (close) {
                    closeFlag = "Y";
                } else if (drop) {
                    closeFlag = "D";
                } else {
                    closeFlag = "N";
                }
                closeDate = closeDate;
                closeUser = closedUser;
            }

            if (changeStep != null && changeStep.isEmpty()) {
                currentStep = changeStep;
            }
            qmsQmsMrbMasterRepository.save(new QmsMrbMaster(plant, systemName, mrbNumber, revisionNo, issueDate, title, process, site, problem, cause, qmsMrbMaster.getMeasures(), qmsMrbMaster.getDevice(), problemDesc, external ? "Y" : "N", validation ? "Y" : "N", closeFlag, causeDesc, qmsMrbMaster.getRegDate(), qmsMrbMaster.getRegUser(), closeDate, closeUser, currentStep, lotDisposition, reason));
        } else {
            qmsQmsMrbMasterRepository.save(new QmsMrbMaster(plant, systemName, mrbNumber, revisionNo, issueDate, title, process, site, problem, cause, "", "", problemDesc, external ? "Y" : "N", validation ? "Y" : "N", "N", causeDesc, regDate, regUser, "", "", "00", lotDisposition, reason));
        }

        //QMS_MRB_LOT_LIST 삭제
        qmsQmsMrbLotListRepository.deleteAllByPlantAndSystemNameAndQmsNumber(plant, systemName, mrbNumber);
        if (nonConfList != null && !nonConfList.isEmpty()) {
            for (int i = 0; i < nonConfList.size(); i++) {
                qmsQmsMrbLotListRepository.save(new QmsMrbLotList(plant, systemName, mrbNumber, revisionNo, nonConfList.get(i).getLotNumber(), nonConfList.get(i).getDevice(), nonConfList.get(i).getWaferQty()
                        , nonConfList.get(i).getPcsQty(), nonConfList.get(i).getRemark(), nonConfList.get(i).getPkgType(), nonConfList.get(i).getPkgSize(), nonConfList.get(i).getPkgLot(), nonConfList.get(i).getFaillRate()));
            }
        }
        //QMS_ACTION_ITEM_LIST 삭제
        qmsQmsActionItemListRepository.deleteAllByPlantAndSystemNameAndQmsNumber(plant, systemName, mrbNumber);

        if (actionItemList != null && !actionItemList.isEmpty()) {
            for (QmsActionItemListDto qmsActionItemListDto : actionItemList) {
                qmsQmsActionItemListRepository.save(new QmsActionItemList(plant, systemName, mrbNumber, qmsActionItemListDto.getSeqNo(), qmsActionItemListDto.getActionItem(), revisionNo, "", qmsActionItemListDto.getUserName(), qmsActionItemListDto.getCompletedDate(), qmsActionItemListDto.getRemark(), "Y"));
            }
        }


        //QMS_SELECTED_LIST
        qmsSelectedListRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, mrbNumber, revisionNo);

        if (memberList != null && memberList.isEmpty()) {
            for (CommonUserInfoDto commonUserInfoDto : memberList) {
                qmsSelectedListRepository.save(new QmsSelectedList(plant, systemName, mrbNumber, revisionNo, "MEMBER", commonUserInfoDto.getUserId(), commonUserInfoDto.getUserId()));
            }
        }

        //QMS_ATTACHED_FILE 삭제
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndSystemNameMtypeAndRevisionNo(plant, systemName, mrbNumber, "QMS_MRB_REPORT", "01");


        if (reportList != null && !reportList.isEmpty()) {
            //QMS_ATTACHED_FILE 입력
            for (int i = 0; i < reportList.size(); i++) {
                int attachedFileSeq = qmsCommonDao.getQmsAttachedFileSeq(plant, systemName, "QMS_MRB_REPORT", i + "", mrbNumber, "01");
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(formatter);

                qmsAttacheFileRepository.save(new QmsAttachFile(plant, systemName, "QMS_MRB_REPORT", i + "", mrbNumber, revisionNo, formattedDate, "", attachedFileSeq, reportList.get(i).getRealFileId(), reportList.get(i).getOrgFileId(), reportList.get(i).getFileRemark(), reportList.get(i).getFilePath(), "", "", "", "", "", ""));
            }
        }

        if (stepFlag.equals("999")) {
            qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndSystemNameMtypeAndRevisionNo(plant, systemName, mrbNumber, "SUPPLIER", revisionNo);

            if (customerReportList != null & customerReportList.isEmpty()) {
                for (int i = 0; i < customerReportList.size(); i++) {

                    int attachedFileSeq = qmsCommonDao.getQmsAttachedFileSeq(plant, systemName, "SUPPLIER", i + "", mrbNumber, "01");
                    LocalDateTime now = LocalDateTime.now();
                    String formattedDate = now.format(formatter);
                    qmsAttacheFileRepository.save(new QmsAttachFile(plant, systemName, "SUPPLIER", i + "", mrbNumber, revisionNo, formattedDate, "", attachedFileSeq, customerReportList.get(i).getRealFileId(), customerReportList.get(i).getOrgFileId(), customerReportList.get(i).getFileRemark(), customerReportList.get(i).getFilePath(), "", customerReportList.get(i).getDate(), customerReportList.get(i).getApproveDate(), "", "", ""));
                }
            }
        }

        //QMS_ATTACHED_FILE 삭제
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndSystemNameMtypeAndRevisionNo(plant, systemName, mrbNumber, "VALIDATION", "01");


        if (validationList != null & validationList.isEmpty()) {
            for (int i = 0; i < validationList.size(); i++) {

                int attachedFileSeq = qmsCommonDao.getQmsAttachedFileSeq(plant, systemName, "VALIDATION", i + "", mrbNumber, "01");
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(formatter);
                qmsAttacheFileRepository.save(new QmsAttachFile(plant, systemName, "VALIDATION", i + "", mrbNumber, revisionNo, formattedDate, "", attachedFileSeq, customerReportList.get(i).getRealFileId(), customerReportList.get(i).getOrgFileId(), customerReportList.get(i).getFileRemark(), customerReportList.get(i).getFilePath(), "", customerReportList.get(i).getDate(), "", "", "", ""));
            }
        }
        if(insert){
            //문서 등록

        }else if(mailFlag.equals("CH")){

        }

    }

    @Override
    public void mrbReqApproval(MrbManagerDto dto) {
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String plant = dto.getPlant();
            String systemName = dto.getSystemName();
            String mrbNumber =dto.getMrbNumber();
            String title = dto.getTitle();
            String issueDate = dto.getIssueDate();
            String process = dto.getProcess();
            String site = dto.getSite();
            String problemDesc = dto.getProblemDesc();
            String causeDesc = dto.getCauseDesc();
            String problem = dto.getProblem();
            String cause = dto.getCause();
            String revisionNo = "01";
            boolean external = dto.isExternal();
            boolean validation = dto.isValidation();
            String regDate = dto.getRegDate();
            String regUser = dto.getRegUser();
            String lotDisposition = dto.getLotDisposition();
            String reason = dto.getReason();

            boolean isMailError = false;

            String comment = dto.getComment();

        List<QmsMrbLotListDto> nonConfList = dto.getNonConfList();
        List<QmsActionItemListDto> actionItemList = dto.getActionItemList();
        List<QmsApprovalRuleDto> approvalList = dto.getApprovalList();
        List<CommonUserInfoDto> memberList = dto.getMeberList();
        List<QmsAttachFileDto> reportList = dto.getReportList();

        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, mrbNumber, revisionNo);
        if (qmsMrbMaster != null) {

            qmsQmsMrbMasterRepository.save(new QmsMrbMaster(plant, systemName, mrbNumber, revisionNo, issueDate, title, process, site, problem, cause, qmsMrbMaster.getMeasures(), qmsMrbMaster.getDevice(), problemDesc, external ? "Y" : "N", validation ? "Y" : "N", qmsMrbMaster.getClosedFlag(), causeDesc, qmsMrbMaster.getRegDate(), qmsMrbMaster.getRegUser(), qmsMrbMaster.getClosedDate(), qmsMrbMaster.getClosedUser(), "10", lotDisposition, reason));
        } else {
            qmsQmsMrbMasterRepository.save(new QmsMrbMaster(plant, systemName, mrbNumber, revisionNo, issueDate, title, process, site, problem, cause, "", "", problemDesc, external ? "Y" : "N", validation ? "Y" : "N", "N", causeDesc, regDate, regUser, "", "", "10", lotDisposition, reason));
        }

        //QMS_MRB_LOT_LIST 삭제
        qmsQmsMrbLotListRepository.deleteAllByPlantAndSystemNameAndQmsNumber(plant, systemName, mrbNumber);
        if (nonConfList != null && !nonConfList.isEmpty()) {
            for (int i = 0; i < nonConfList.size(); i++) {
                qmsQmsMrbLotListRepository.save(new QmsMrbLotList(plant, systemName, mrbNumber, revisionNo, nonConfList.get(i).getLotNumber(), nonConfList.get(i).getDevice(), nonConfList.get(i).getWaferQty()
                        , nonConfList.get(i).getPcsQty(), nonConfList.get(i).getRemark(), nonConfList.get(i).getPkgType(), nonConfList.get(i).getPkgSize(), nonConfList.get(i).getPkgLot(), nonConfList.get(i).getFaillRate()));
            }
        }

        //QMS_ACTION_ITEM_LIST 삭제
        qmsQmsActionItemListRepository.deleteAllByPlantAndSystemNameAndQmsNumber(plant, systemName, mrbNumber);

        if (actionItemList != null && !actionItemList.isEmpty()) {
            for (QmsActionItemListDto qmsActionItemListDto : actionItemList) {
                qmsQmsActionItemListRepository.save(new QmsActionItemList(plant, systemName, mrbNumber, qmsActionItemListDto.getSeqNo(), qmsActionItemListDto.getActionItem(), revisionNo, "", qmsActionItemListDto.getUserName(), qmsActionItemListDto.getCompletedDate(), qmsActionItemListDto.getRemark(), "Y"));
            }
        }
        qmsSelectedListRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, mrbNumber, revisionNo);


        //QMS_SELECTED_LIST 삭제
        qmsSelectedListRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, mrbNumber, revisionNo);

        if (memberList != null && memberList.isEmpty()) {
            for (CommonUserInfoDto commonUserInfoDto : memberList) {
                qmsSelectedListRepository.save(new QmsSelectedList(plant, systemName, mrbNumber, revisionNo, "MEMBER", commonUserInfoDto.getUserId(), commonUserInfoDto.getUserId()));
            }
        }

        //QMS_ATTACHED_FILE 삭제
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndSystemNameMtypeAndRevisionNo(plant, systemName, mrbNumber, "QMS_MRB_REPORT", "01");


        if (reportList != null && !reportList.isEmpty()) {
            //QMS_ATTACHED_FILE 입력
            for (int i = 0; i < reportList.size(); i++) {
                int attachedFileSeq = qmsCommonDao.getQmsAttachedFileSeq(plant, systemName, "QMS_MRB_REPORT", i + "", mrbNumber, "01");
                LocalDateTime now = LocalDateTime.now();
                String formattedDate = now.format(formatter);
                qmsAttacheFileRepository.save(new QmsAttachFile(plant, systemName, "QMS_MRB_REPORT", i + "", mrbNumber, revisionNo, formattedDate, "", attachedFileSeq , reportList.get(i).getRealFileId(), reportList.get(i).getOrgFileId(), reportList.get(i).getFileRemark(), reportList.get(i).getFilePath(), "", "", "", "", "", ""));
            }
        }

        if(approvalList != null && !approvalList.isEmpty()){
            qmsQmsApprovalRuleRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName,mrbNumber, "01");
            for(QmsApprovalRuleDto qmsApprovalRuleDto : approvalList ){
                if(qmsApprovalRuleDto.getStatusR().equals("E")){
                    LocalDateTime now = LocalDateTime.now();
                    String formattedDate = now.format(formatter);
                    qmsQmsApprovalRuleRepository.save(new QmsApprovalRule(plant,systemName, mrbNumber,revisionNo, qmsApprovalRuleDto.getRoleId(), qmsApprovalRuleDto.getRoleId().equals("10") ? formattedDate: "",qmsApprovalRuleDto.getRoleId().equals("10")  ? "Y": "N",qmsApprovalRuleDto.getUserId(),qmsApprovalRuleDto.getRoleId().equals("10") ? comment: ""));
                }
            }
        }
        //메일 전송



    }

    @Override
    public void mrbDelte(MrbManagerDto dto) {
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String qmsNumber = dto.getQmsNumber();
        String revisionNo = "01";

        qmsQmsMrbMasterRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant, systemName, qmsNumber,revisionNo);
        qmsQmsMrbLotListRepository.deleteAllByPlantAndSystemNameAndQmsNumber(plant,systemName,qmsNumber);
        qmsQmsActionItemListRepository.deleteAllByPlantAndSystemNameAndQmsNumber(plant,systemName,qmsNumber);
        qmsSelectedListRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant,systemName,qmsNumber,revisionNo);
        qmsAttacheFileRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant,systemName,qmsNumber,revisionNo);
        qmsQmsApprovalRuleRepository.deleteAllByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant,systemName,qmsNumber,revisionNo);


    }

    @Override
    public void changeStep(MrbManagerDto dto) {
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String mrbNumber = dto.getMrbNumber();
        String step = dto.getStep();
        String userId = dto.getUserId();
        String revisionNo = "01";
        String mailFlag = dto.getMailFlag();
        String comment = "";
        String title = "";
        List<QmsApprovalRuleDto> approvalList = null;
        List<CommonUserInfoDto> memberList = null;
        boolean isMailError = false;

        if(mailFlag != null && !mailFlag.isEmpty()){
            memberList = dto.getMeberList();
            approvalList = dto.getApprovalList();
            title = dto.getTitle();
            comment = dto.getComment();
        }

        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant,systemName,mrbNumber,revisionNo);
        if(qmsMrbMaster != null){
            qmsMrbMaster.updateCurrentStep(step);
            qmsQmsMrbMasterRepository.save(qmsMrbMaster);
        }

        if(mailFlag.equals("CH")){
            boolean reviewerNFlag = false;
            boolean approvalYFlag = false;
            boolean approvalNFlag = false;
            boolean sendMail = false;

            for(QmsApprovalRuleDto approvalRuleDto : approvalList){
                if(approvalRuleDto.getRoleId().equals("50")){
                    if(approvalRuleDto.getStatusR().equals("N")){
                        reviewerNFlag = true;
                    }
                }

                if(approvalRuleDto.getRoleId().equals("60")){
                    if(approvalRuleDto.getStatusR().equals("Y")){
                        approvalYFlag = true;
                    }
                }

                if(approvalRuleDto.getRoleId().equals("60")){
                    if(approvalRuleDto.getStatusR().equals("N")){
                        approvalNFlag = true;
                    }
                }
            }

            if (reviewerNFlag == false && approvalYFlag == false && approvalNFlag == true)
            {//Reviewer: 모두Y Approval: 모두N (결재시)
                sendMail = true;
            }
            else if (reviewerNFlag == false && approvalYFlag == true && approvalNFlag == false)
            {//Reviewer: 모두Y Approval: 모두Y (완료시)
                sendMail = true;
            }

            //메일 전송

        }

    }

    @Override
    public void changeDrafter(MrbManagerDto dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String plant = dto.getPlant();
        String systemName = dto.getSystemName();
        String qmsNumber =dto.getQmsNumber();
        String revisionNo = dto.getRevisionNo();
        String changeUser = dto.getChangeUser();
        String regUser = dto.getRegUser();
        String userId = dto.getUserId();
        String remark = dto.getRemark();
        String title = dto.getTitle();

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(formatter);
        qmsSysChangeDrafterLogRepository.save(new SysChangeDrafterLog(plant,systemName,qmsNumber,revisionNo,formattedDate, title,regUser,changeUser,userId,remark ));
        QmsMrbMaster qmsMrbMaster = qmsQmsMrbMasterRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNo(plant,systemName,qmsNumber,revisionNo);
        if(qmsMrbMaster != null){
            qmsMrbMaster.updateRegUser(changeUser);
            qmsQmsMrbMasterRepository.save(qmsMrbMaster);
        }

        List<QmsApprovalRule> qmsApprovalRuleList = qmsQmsApprovalRuleRepository.findByPlantAndSystemNameAndQmsNumberAndRevisionNoAndApprovalTypeAndUserId(plant, systemName, qmsNumber, revisionNo,"10", regUser);

        if(qmsApprovalRuleList != null && !qmsApprovalRuleList.isEmpty()){
            for(QmsApprovalRule qmsApprovalRule : qmsApprovalRuleList){
                qmsApprovalRule.updateUserId(changeUser);
                qmsQmsApprovalRuleRepository.save(qmsApprovalRule);
            }
        }

    }
}
