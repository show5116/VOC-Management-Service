package com.vms.server.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmRoleGroupDto {
    private String plant;
    private String roleGroup;
    private String description;
    private String roleType;
    private String regTime;
    private String regUser;
    private String dml;
}
