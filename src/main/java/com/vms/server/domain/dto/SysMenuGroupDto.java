package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuGroupDto {
    private String plant;
    private String moduleId;
    private String groupId;
    private String groupName;
    private String visibleFlag;
    private String description;
    private String expandFlag1;
    private String expandFlag2;
    private String expandFlag3;
    private String expandFlag4;
    private String expandFlag5;
    private String expandField1;
    private String expandField2;
    private String expandField3;
    private String expandField4;
    private String expandField5;

    private int rownum;
    private String moduleDesc;
    private String orderSeq;
    private String dml;

    public SysMenuGroupDto(String moduleId, String moduleDesc, String orderSeq) {
        this.moduleId = moduleId;
        this.moduleDesc = moduleDesc;
        this.orderSeq = orderSeq;
    }
}
