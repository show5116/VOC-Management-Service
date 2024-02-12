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
@Table(name = "QMS_KPI_MASTER_TEMP")
public class QmsKpiMasterTemp {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Column(name = "KPI_NO")
  public String kpiNo;
  @Column(name = "KPI")
  public String kpi;
  @Column(name = "DOC_NUMBER")
  public String docNumber;
  @Column(name = "TYPE")
  public String type;
  @Column(name = "CLASSIFICATION")
  public String classification;
  @Column(name = "CALC_FORMULA")
  public String calcFormula;
  @Column(name = "CYCLE")
  public String cycle;
  @Column(name = "UNIT")
  public String unit;
  @Column(name = "USE_FLAG")
  public String useFlag;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
