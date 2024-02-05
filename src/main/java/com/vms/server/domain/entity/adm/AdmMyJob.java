package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmMyJobId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmMyJobId.class)
@Table(name = "ADM_MY_JOB")
public class AdmMyJob {
  @Id
  @Column(name = "EMP_CODE")
  public String empCode;
  @Id
  @Column(name = "DEPT_ID")
  public String deptId;
  @Column(name = "OFC_ID")
  public String ofcId;
  @Column(name = "DEPT_ORDER")
  public String deptOrder;
  @Column(name = "DEFAULT_YN")
  public String defaultYn;

  public AdmMyJob(String empCode, String deptId, String ofcId, String defaultYn) {
    this.empCode = empCode;
    this.deptId = deptId;
    this.ofcId = ofcId;
    this.defaultYn = defaultYn;
  }
}
