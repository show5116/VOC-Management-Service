package com.vms.server.voc.dto.response;

import com.vms.server.domain.entity.qms.QmsAttachFile;
import com.vms.server.domain.entity.qms.QmsVocStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VocListResponse {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String issueDate;
    private String customer;
    private String classification;
    private String requiredResponseDate;
    private String remark;
    private String closedDate;
    private String closedUser;
    private String personInCharge;
    private String requirement;
    private String updateDate;
    private String updateUser;
    private String fileId;
    private String fileName;

    public VocListResponse(QmsVocStatus qmsVocStatus) {
        this.plant = qmsVocStatus.getId().getPlant();
        this.systemName = qmsVocStatus.getId().getSystemName();
        this.qmsNumber = qmsVocStatus.getId().getQmsNumber();
        this.revisionNo = qmsVocStatus.getId().getRevisionNo();
        this.issueDate = qmsVocStatus.getIssueDate();
        this.customer = qmsVocStatus.getCustomer();
        this.classification = qmsVocStatus.getClassification();
        this.requiredResponseDate = qmsVocStatus.getRequiredResponseDate();
        this.remark = qmsVocStatus.getRemark();
        this.closedDate = qmsVocStatus.getClosedDate();
        this.closedUser = qmsVocStatus.getClosedUser();
        this.personInCharge = qmsVocStatus.getPersonInCharge();
        this.requirement = qmsVocStatus.getRequirement();
        this.updateDate = qmsVocStatus.getUpdateDate();
        this.updateUser = qmsVocStatus.getUpdateUser();
        this.fileId = qmsVocStatus.getAttachFiles()
                .stream().map(QmsAttachFile::getRealFileId)
                .collect(Collectors.joining(","));
        this.fileName = qmsVocStatus.getAttachFiles()
                .stream().map(QmsAttachFile::getOrgFileId)
                .collect(Collectors.joining(","));
    }
}
