package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRfaAnalysisId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRfaAnalysisId.class)
@Table(name = "QMS_RFA_ANALYSIS")
public class QmsRfaAnalysis {
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
  @Column(name = "SEQ")
  public String seq;
  @Column(name = "REQ_DATE")
  public String reqDate;
  @Column(name = "FA_PROCESS")
  public String faProcess;
  @Column(name = "FA_QTY")
  public String faQty;
  @Column(name = "FA_REQ")
  public String faReq;
  @Column(name = "SUB_TOTAL")
  public String subTotal;
  @Column(name = "COMP_DATE")
  public String compDate;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
}
