package com.vms.server.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsMrbLotListDto {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String lotNumber;
    private String device;
    private String waferQty;
    private String pcsQty;
    private String remark;
    private String pkgType;
    private String pkgSize;
    private String pkgLot;
    private String faillRate;
}
