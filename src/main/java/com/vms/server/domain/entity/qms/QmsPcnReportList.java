package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPcnReportListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPcnReportListId.class)
@Table(name = "QMS_PCN_REPORT_LIST")
public class QmsPcnReportList {
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
  @Column(name = "ECN_NUMBER")
  public String ecnNumber;
  @Id
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Column(name = "PROPOSAL_PRT_FILE")
  public String proposalPrtFile;
  @Column(name = "PROPOSAL_PRT_DATE")
  public String proposalPrtDate;
  @Column(name = "APPROVLA_PRT_FILE")
  public String approvlaPrtFile;
  @Column(name = "APPROVLA_PRT_DATE")
  public String approvlaPrtDate;
  @Column(name = "CUSTOMER_APPROVLA")
  public String customerApprovla;
  @Column(name = "APPROVAL_DATE")
  public String approvalDate;
  @Column(name = "CUSTOMER_DOC")
  public String customerDoc;
  @Column(name = "REMARK")
  public String remark;
  @Id
  @Column(name = "REPORT_SEQ")
  public String reportSeq;
}
