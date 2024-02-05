package com.vms.server.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmDeptMappingDto {
    private String plant;
    private String deptBu;
    private String deptId;
    private String functionType;
    private String updateDate;
    private String updateUser;
}
