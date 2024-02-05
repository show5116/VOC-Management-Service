package com.vms.server.domain.vo;

import com.vms.server.domain.dto.SysMenuDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysMenuVo {
    private int id;
    private int parent;
    private boolean droppable = true;
    private String text;
    private SysMenuDto sysMenuDtos;
}
