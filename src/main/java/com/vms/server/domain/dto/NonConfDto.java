package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonConfDto {
    private String seq;
    private String lotNumber;
    private String product;
    private String pkgType;
    private String pkgSize;
    private String waferCnt;
    private String pkgLot;
    private String pcsCnt;
    private String faillRate;
    private String remark;

}
