package com.vms.server.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuStructureVo {
    private int menuDepth;
    private String menuName;
    private String menuPath;
    private String componentPath;
    private int menuKey;
    private int parentKey;
    private List<SysMenuStructureVo> children;

    public SysMenuStructureVo(int menuDepth, String menuName, String menuPath, String componentPath, int menuKey, int parentKey) {
        this.menuDepth = menuDepth;
        this.menuName = menuName;
        this.menuPath = menuPath;
        this.componentPath = componentPath;
        this.menuKey = menuKey;
        this.parentKey = parentKey;
    }
}
