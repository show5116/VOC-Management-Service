package com.vms.server.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmDeptVo {
    private String deptId;
    private String deptName;
    private String deptComment;
    private String deptOrder;
    private String upDeptIdx;
    private String deptStatus;
    private String deptLoc;
    private String deptDepth;
    private String deptLeader;
    private String bu;
    private String buId;
}
