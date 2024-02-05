package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmUserInfoDto{
    private String plant ="";
    private String userId ="";
    private String userName ="";
    private String description ="";
    private String password ="";
    private String passwordMd5 ="";
    private String languageType ="";
    private String department ="";
    private String externalFlag ="";
    private String externalType ="";
    private String emailId ="";
    private String phoneNo ="";
    private String roleId ="";
    private String accessGroupId ="";
    private String admission ="";
    private String expireDate ="";
    private String signImage ="";
    private String regTime ="";
    private String regUser ="";
    private String expandFlag1 ="";
    private String expandFlag2 ="";
    private String expandFlag3 ="";
    private String expandFlag4 ="";
    private String expandFlag5 ="";
    private String expandField1 ="";
    private String expandField2 ="";
    private String expandField3 ="";
    private String expandField4 ="";
    private String expandField5 ="";
    private String superUser ="";
    private String abnLevel="";
    private String abnAbsence ="";
    private String createdTime ="";
    private String createdUser ="";
    private String modifiedTime ="";
    private String modifiedUser ="";
    private boolean subconstractorYN;
    private List<AdmUserInfoDto> children = new ArrayList<AdmUserInfoDto>();
    private List<String> departments = new ArrayList<String>();
    private String subcontractor ="";
    private String subcontractorCode="";
    private String deptName;
    private String empCode ="";
    private String deptId="";
    private String posName="";
    private String email="";
    private String ofcName="";
    private String empName="";
    private String formatEmail="";
    private List<String> userIds;
    private List<String> userNames;

    private String code;

    public AdmUserInfoDto(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public AdmUserInfoDto(String plant, String department, String subcontractor, String roleId, String userId, String userName, String emailId, String externalFlag, String id, String phoneNo, String s, String admission, String subcontractorCode) {

        this.plant = plant;
        this.department = department;
        this.subcontractor = subcontractor;
        this.roleId = roleId;
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
    }


    @Override
    public String toString() {
        return "AdmUserInfoDto{" +
                "plant='" + plant + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                ", roleId='" + roleId + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
