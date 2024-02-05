package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmDeptMappingId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmDeptMappingId.class)
@Table(name = "ADM_DEPT_MAPPING")
public class AdmDeptMapping {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "DEPT_BU")
  public String deptBu;
  @Id
  @Column(name = "DEPT_ID")
  public String deptId;
  @Column(name = "FUNCTION_TYPE")
  public String functionType;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;

  public AdmDeptMapping(String plant, String deptBu, String deptId, String functionType, String updateDate, String updateUser) {
    this.plant = plant;
    this.deptBu = deptBu;
    this.deptId = deptId;
    this.functionType = functionType;
    this.updateDate = updateDate;
    this.updateUser = updateUser;
  }
}
