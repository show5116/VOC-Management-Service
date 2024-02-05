package com.vms.server.domain.dto;

import com.vms.server.domain.entity.adm.AdmDept;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmDeptDto {
    private String deptId;
    private String deptName;
    private String deptComment;
    private Integer deptOrder;
    private String upDeptIdx;
    private String deptStatus;
    private String deptLoc;
    private Integer deptDepth;
    private String deptLeader;
    private String bu;
    private String buId;
    private String plant;
    private List<AdmDeptDto> child = new ArrayList<>();

    public AdmDeptDto(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public AdmDeptDto(AdmDept admDeptEntity) {
        this.deptId = admDeptEntity.getDeptId();
        this.deptName = admDeptEntity.getDeptName();
        this.deptComment = admDeptEntity.getDeptComment();
        this.deptOrder = admDeptEntity.getDeptOrder();
        this.upDeptIdx = admDeptEntity.getUpDeptIdx();
        this.deptStatus = admDeptEntity.getDeptStatus();
        this.deptLoc = admDeptEntity.getDeptLoc();
        this.deptDepth = admDeptEntity.getDeptDepth();
        this.deptLeader = admDeptEntity.getDeptLeader();
    }


    public void setChild(AdmDeptDto admDeptDto){
        child.add(admDeptDto);
    }

}
