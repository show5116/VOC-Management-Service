package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdmUserCodeDto {
    private String plant;
    private String tableName;
    private String description;
    private String tableType;
    private String formatType;
    private int decimalLength;
    private int sizeLimit;
    private String regTime;
    private String regUser;
    private String dml;
}
