package com.vms.server.domain.entity.qms;

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
@Table(name = "QMS_KPI_LIST_TEMP")
public class QmsKpiListTemp {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Column(name = "REVISION_NO")
  public String revisionNo;
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
}
