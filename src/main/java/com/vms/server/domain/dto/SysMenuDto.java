package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuDto {
    private String plant;
    private String groupId;
    private String menuId;
    private String isActionMenu;
    private String menuName;
    private String menuNameKor;
    private String menuNameEng;
    private String menuName01;
    private String menuName02;
    private String menuName03;
    private String hasChild;
    private String parentKey;
    private int displayDepth;
    private String toolbarIcon;
    private String tooltip;
    private String etooltip;
    private String path;
    private String seperateBar;
    private String actionSeq;
    private String actionType;
    private String action;
    private String shortcutKey;
    private String controllType;
    private String controllValue;
    private String visibleFlag;
    private String enabledFlag;
    private String adminViewmode;
    private String description;
    private String expandField1;
    private String expandField2;
    private String expandField3;
    private String expandField4;
    private String expandField5;
    private String actExpandField1;
    private String actExpandField2;
    private String actExpandField3;
    private String actExpandField4;
    private String actExpandField5;

    private List<SysMenuDto> children = new ArrayList<>();

    public SysMenuDto(String plant, String groupId, String menuId, String isActionMenu, String menuName, String menuNameKor, String menuNameEng, String menuName01, String menuName02, String menuName03, String hasChild, String parentKey, int displayDepth, String toolbarIcon, String path, String seperateBar, String actionSeq, String actionType, String action, String shortcutKey, String controllType, String controllValue, String visibleFlag, String enabledFlag, String adminViewmode, String description, String expandField1, String expandField2, String expandField3, String expandField4, String expandField5, String actExpandField1, String actExpandField2, String actExpandField3, String actExpandField4, String actExpandField5) {
        this.plant = plant;
        this.groupId = groupId;
        this.menuId = menuId;
        this.isActionMenu = isActionMenu;
        this.menuName = menuName;
        this.menuNameKor = menuNameKor;
        this.menuNameEng = menuNameEng;
        this.menuName01 = menuName01;
        this.menuName02 = menuName02;
        this.menuName03 = menuName03;
        this.hasChild = hasChild;
        this.parentKey = parentKey;
        this.displayDepth = displayDepth;
        this.toolbarIcon = toolbarIcon;
        this.path = path;
        this.seperateBar = seperateBar;
        this.actionSeq = actionSeq;
        this.actionType = actionType;
        this.action = action;
        this.shortcutKey = shortcutKey;
        this.controllType = controllType;
        this.controllValue = controllValue;
        this.visibleFlag = visibleFlag;
        this.enabledFlag = enabledFlag;
        this.adminViewmode = adminViewmode;
        this.description = description;
        this.expandField1 = expandField1;
        this.expandField2 = expandField2;
        this.expandField3 = expandField3;
        this.expandField4 = expandField4;
        this.expandField5 = expandField5;
        this.actExpandField1 = actExpandField1;
        this.actExpandField2 = actExpandField2;
        this.actExpandField3 = actExpandField3;
        this.actExpandField4 = actExpandField4;
        this.actExpandField5 = actExpandField5;
    }

    public SysMenuDto(SysMenuDto sysMenuDto) {
    }
}
