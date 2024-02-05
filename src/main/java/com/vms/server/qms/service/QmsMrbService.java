package com.vms.server.qms.service;

import com.vms.server.domain.dto.*;

import java.util.List;

public interface QmsMrbService {

    List<MrbSearchDto> getMrbSearch(MrbSearchDto dto);

    List<QmsMrbLotListDto> getLoadNonConf(String plant, String systemName, String qmsNumber);

    List<QmsActionItemListDto> getLoadActionItem(String plant, String systemName, String qmsNumber);

    List<QmsAttachFileDto> getLoadAttachedFile(String plant, String systemName, String qmsNumber, String systemNameType);

    List<CommonUserInfoDto> selectedMemberList(String plant, String systemName, String qmsNumber, String revisionNo);

    List<QmsApprovalRuleDto> getApprovalList(String plant, String systemName, String qmsNumber, String revisionNo);

    void mrbClose(MrbManagerDto dto) throws Exception;

    void mrbReject(MrbManagerDto dto);

    void mrbEntrust(MrbManagerDto dto);

    void mrbApproval(MrbManagerDto dto);

    void mrbMerge(MrbManagerDto dto);

    void mrbReqApproval(MrbManagerDto dto);

    void mrbDelte(MrbManagerDto dto);

    void changeStep(MrbManagerDto dto);

    void changeDrafter(MrbManagerDto dto);
}
