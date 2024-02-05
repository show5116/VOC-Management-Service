package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsKpiResultStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsKpiResultStatusId.class)
@Table(name = "QMS_KPI_RESULT_STATUS")
public class QmsKpiResultStatus {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Column(name = "REG_YEAR")
  public String regYear;
  @Column(name = "TYPE")
  public String type;
  @Column(name = "PROCESS")
  public String process;
  @Column(name = "SUBCONTRACT")
  public String subcontract;
  @Column(name = "DEPT_BU")
  public String deptBu;
  @Column(name = "DEPT_ID")
  public String deptId;
  @Column(name = "FUNCTION_TYPE")
  public String functionType;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
