package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdmUserCodeDataDto {
    private String plant;
    private String tableName;
    private String codeName;
    private String codeSeq;
    private String description;
    private String codeGroup1;
    private String codeGroup2;
    private String codeGroup3;
    private String regTime;
    private String regUser;
}
