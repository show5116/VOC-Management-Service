package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmRoleMenuSearchDto extends SearchCriteriaDto{
    private String plant;
    private String searchOrder;
}
