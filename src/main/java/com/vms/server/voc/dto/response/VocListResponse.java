package com.vms.server.voc.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VocListResponse {
    private String plant;
    private String systemName;
    private String qmsNumber;
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

    @QueryProjection
    public VocListResponse(String plant, String systemName, String qmsNumber, String issueDate, String customer, String classification, String requiredResponseDate, String remark, String closedDate, String closedUser, String personInCharge, String requirement, String updateDate, String updateUser, String fileId, String fileName) {
        this.plant = plant;
        this.systemName = systemName;
        this.qmsNumber = qmsNumber;
        this.issueDate = issueDate;
        this.customer = customer;
        this.classification = classification;
        this.requiredResponseDate = requiredResponseDate;
        this.remark = remark;
        this.closedDate = closedDate;
        this.closedUser = closedUser;
        this.personInCharge = personInCharge;
        this.requirement = requirement;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
        this.fileId = fileId;
        this.fileName = fileName;
    }
}
