package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuStructureDto {
    private String plant;
    private String groupId;
    private String moduleId;
    private String menuId;
    private String menuNameKor;
    private String menuNameEng;
    private String menuName01;
    private String menuName02;
    private String menuName03;
    private String hasChild;
    private String parentKey;
    private int displayDepth;
    private String path;
    private int actionSeq;
    private String shortcutKey;
    private String seperateBar;
    private String visibleFlag;
    private String enableFlag;
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
    private String dml;
}
