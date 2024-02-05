package com.vms.server.domain.dto;

import com.vms.server.domain.entity.sys.SysSystemCodeData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemCodeMappingDataDto {
    private String value;
    private String displayValue;
    private String description;
    private String codeName;

    public SystemCodeMappingDataDto(SysSystemCodeData vo) {
        this.value = vo.getCodeName();
        this.displayValue = vo.getDescription();
        this.description = vo.getDescription();
        this.codeName = vo.getCodeName();
    }
}
