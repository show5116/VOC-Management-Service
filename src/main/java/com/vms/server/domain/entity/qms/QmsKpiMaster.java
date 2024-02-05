package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsKpiMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsKpiMasterId.class)
@Table(name = "QMS_KPI_MASTER")
public class QmsKpiMaster {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "KPI_NO")
  public String kpiNo;
  @Column(name = "REG_YEAR")
  public String regYear;
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
  @Column(name = "UNIT")
  public String unit;
  @Column(name = "CYCLE_1M")
  public String cycle1M;
  @Column(name = "CYCLE_2M")
  public String cycle2M;
  @Column(name = "CYCLE_3M")
  public String cycle3M;
  @Column(name = "CYCLE_4M")
  public String cycle4M;
  @Column(name = "CYCLE_5M")
  public String cycle5M;
  @Column(name = "CYCLE_6M")
  public String cycle6M;
  @Column(name = "CYCLE_7M")
  public String cycle7M;
  @Column(name = "CYCLE_8M")
  public String cycle8M;
  @Column(name = "CYCLE_9M")
  public String cycle9M;
  @Column(name = "CYCLE_10M")
  public String cycle10M;
  @Column(name = "CYCLE_11M")
  public String cycle11M;
  @Column(name = "CYCLE_12M")
  public String cycle12M;
  @Column(name = "CYCLE_1Q")
  public String cycle1Q;
  @Column(name = "CYCLE_2Q")
  public String cycle2Q;
  @Column(name = "CYCLE_3Q")
  public String cycle3Q;
  @Column(name = "CYCLE_4Q")
  public String cycle4Q;
  @Column(name = "CYCLE_1H")
  public String cycle1H;
  @Column(name = "CYCLE_2H")
  public String cycle2H;
  @Column(name = "CYCLE_1Y")
  public String cycle1Y;
  @Column(name = "USE_FLAG")
  public String useFlag;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
