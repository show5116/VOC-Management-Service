package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SysSystemCodeAndDataDto {
    private String plant;
    private String tableName;
    private String description;
    private String formatType;
    private String decimalLength;
    private String sizeLimit;

    private String codeName;
    private String codeSeq;
    private String dataDescription;
    private String codeGroup1;
    private String codeGroup2;
    private String codeGroup3;
    private String specialData1;
    private String specialData2;
    private String specialData3;

    private String dml;
}
