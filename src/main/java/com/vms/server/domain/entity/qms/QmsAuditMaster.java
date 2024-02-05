package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsAuditMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsAuditMasterId.class)
@Table(name = "QMS_AUDIT_MASTER")
public class QmsAuditMaster {
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
  @Column(name = "AUDIT_KIND")
  public String auditKind;
  @Column(name = "AUDIT_TYPE")
  public String auditType;
  @Column(name = "AUDITOR")
  public String auditor;
  @Column(name = "AUDITEE")
  public String auditee;
  @Column(name = "AUDIT_FROM_DATE")
  public String auditFromDate;
  @Column(name = "AUDIT_TO_DATE")
  public String auditToDate;
  @Column(name = "AUDIT_RESULT_SCORE")
  public String auditResultScore;
  @Column(name = "AUDIT_RESULT_GRADE")
  public String auditResultGrade;
  @Column(name = "AUDIT_RESULT_REVIEW")
  public String auditResultReview;
  @Column(name = "RETURN_REMARK")
  public String returnRemark;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "CURRENT_STEP")
  public String currentStep;
  @Column(name = "FIND_FLAG")
  public String findFlag;
}
