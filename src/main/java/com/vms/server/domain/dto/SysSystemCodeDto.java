package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SysSystemCodeDto {
    private String plant;
    private String tableName;
    private String description;
    private String formatType;
    private int decimalLength;
    private int sizeLimit;
    private String groupDesc;
    private String regTime;
    private String regUser;

    public SysSystemCodeDto(String plant, String tableName, String description, String formatType, int decimalLength, int sizeLimit) {
        this.plant = plant;
        this.tableName = tableName;
        this.description = description;
        this.formatType = formatType;
        this.decimalLength = decimalLength;
        this.sizeLimit = sizeLimit;
    }
}
