package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsEvnReportDataId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsEvnReportDataId.class)
@Table(name = "QMS_EVN_REPORT_DATA")
public class QmsEvnReportData {
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
  @Id
  @Column(name = "REPORT_NO")
  public String reportNo;
  @Column(name = "REPORT_DATE")
  public String reportDate;
  @Column(name = "EXPIRED_DATE")
  public String expiredDate;
  @Column(name = "ANALYSIS_LAB")
  public String analysisLab;
}
