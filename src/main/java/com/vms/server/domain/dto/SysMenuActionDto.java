package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuActionDto {
    private int actionSeq;
    private String moduleId;
    private String actionType;
    private String actionNameKor;
    private String actionNameEng;
    private String actionName01;
    private String actionName02;
    private String actionName03;
    private String action;
    private String controlType;
    private String controlValue;
    private String enableToolbar;
    private String toolbarIcon;
    private String toolbarTextKor;
    private String toolbarTextEng;
    private String toolbarText01;
    private String toolbarText02;
    private String toolbarText03;
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

    private String plant;
    private int rownum;
    private String dml;
    private String actionTypeName;
    private String enableToolbarName;
    private String menuCount;
}
