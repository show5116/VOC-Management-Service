package com.vms.server.domain.entity.adm;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ADM_DEPT")
public class AdmDept {

  @Id
  @Column(name = "DEPT_ID")
  public String deptId;
  @Column(name = "DEPT_NAME")
  public String deptName;
  @Column(name = "DEPT_COMMENT")
  public String deptComment;
  @Column(name = "DEPT_ORDER")
  public Integer deptOrder;
  @Column(name = "UP_DEPT_IDX")
  public String upDeptIdx;
  @Column(name = "DEPT_STATUS")
  public String deptStatus;
  @Column(name = "DEPT_LOC")
  public String deptLoc;
  @Column(name = "DEPT_DEPTH")
  public Integer deptDepth;
  @Column(name = "DEPT_LEADER")
  public String deptLeader;

  public AdmDept(String deptId, String deptStatus, String deptName,int deptOrder, String upDeptIdx, int deptDepth) {
    this.deptId = deptId;
    this.deptStatus = deptStatus;
    this.deptName = deptName;
    this.deptOrder = deptOrder;
    this.upDeptIdx = upDeptIdx;
    this.deptDepth = deptDepth;
  }

}
