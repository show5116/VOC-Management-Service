package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsKpiListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsKpiListId.class)
@Table(name = "QMS_KPI_LIST")
public class QmsKpiList {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Id
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Column(name = "REG_YEAR")
  public String regYear;
  @Column(name = "TYPE")
  public String type;
  @Column(name = "KPI_NO")
  public String kpiNo;
  @Column(name = "DEPT_BU")
  public String deptBu;
  @Column(name = "DEPT_ID")
  public String deptId;
  @Column(name = "FUNCTION_TYPE")
  public String functionType;
  @Column(name = "PROCESS")
  public String process;
  @Column(name = "WEIGHT")
  public String weight;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;

  public QmsKpiList(String plant, String systemName, String qmsNumber, String revisionNo, String regYear, String type, String kpiNo, String deptBu, String deptId, String functionType, String process, String weight, String regDate, String regUser, String updateDate, String updateUser) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.regYear = regYear;
    this.type = type;
    this.kpiNo = kpiNo;
    this.deptBu = deptBu;
    this.deptId = deptId;
    this.functionType = functionType;
    this.process = process;
    this.weight = weight;
    this.regDate = regDate;
    this.regUser = regUser;
    this.updateDate = updateDate;
    this.updateUser = updateUser;
  }
}
