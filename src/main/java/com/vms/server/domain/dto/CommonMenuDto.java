package com.vms.server.domain.dto;

import com.vms.server.common.LangLocale;
import com.vms.server.domain.entity.sys.SysMenuAction;
import com.vms.server.domain.entity.sys.SysMenuStructure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Getter
@ToString
@NoArgsConstructor
public class CommonMenuDto {

    private String menuId;
    private Integer displayDepth;
    private String path;
    private String displayMenuName;
    private String displayNameKor;
    private String displayNameEng;
    private String displayNameCn;
    private String hasChild;
    private String parentKey;
    private List<CommonMenuDto> childMenu;

    public CommonMenuDto(SysMenuStructure sysMenuStructure){
        this.menuId = sysMenuStructure.getMenuId();
        this.displayDepth = sysMenuStructure.getDisplayDepth();
        this.displayNameKor = sysMenuStructure.getMenuNameKor();
        this.displayNameEng = sysMenuStructure.getMenuNameEng();
        this.displayNameCn = sysMenuStructure.getMenuName01();
        this.hasChild = sysMenuStructure.getHasChild();
        this.parentKey = sysMenuStructure.getParentKey();
    }
    public CommonMenuDto(SysMenuAction sysMenuAction, SysMenuStructure sysMenuStructure) {
        this.menuId = sysMenuStructure.getMenuId();
        this.displayDepth = sysMenuStructure.getDisplayDepth();
        this.path = sysMenuAction.getAction();
        this.displayNameKor = sysMenuStructure.getMenuNameKor();
        this.displayNameEng = sysMenuStructure.getMenuNameEng();
        this.displayNameCn = sysMenuStructure.getMenuName01();
        this.hasChild = sysMenuStructure.getHasChild();
        this.parentKey = sysMenuStructure.getParentKey();

    }

    public void setChildMenu( List<CommonMenuDto> childMenuList ){
        this.childMenu = childMenuList;
    }

    public void displayMenuNameConfig (LangLocale locale) {
        switch (locale) {
            case KO:
                this.displayMenuName = getDisplayNameKor();
                break;
            case CN:
                this.displayMenuName = getDisplayNameCn();
                break;
            default: this.displayMenuName = getDisplayNameEng();
        }
    }

}
