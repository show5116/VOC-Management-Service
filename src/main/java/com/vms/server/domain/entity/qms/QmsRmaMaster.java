package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRmaMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRmaMasterId.class)
@Table(name = "QMS_RMA_MASTER")
public class QmsRmaMaster {
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
  @Column(name = "CURRENT_STEP")
  public String currentStep;
  @Column(name = "ISSUE_TITLE")
  public String issueTitle;
  @Column(name = "ISSUE_DATE")
  public String issueDate;
  @Column(name = "ISSUE_DEVICE")
  public String issueDevice;
  @Column(name = "ISSUE_DEVICE_STATUS")
  public String issueDeviceStatus;
  @Column(name = "ISSUE_CUSTOMER")
  public String issueCustomer;
  @Column(name = "ISSUE_SITE")
  public String issueSite;
  @Column(name = "ISSUE_PROCESS")
  public String issueProcess;
  @Column(name = "ISSUE_MODEL")
  public String issueModel;
  @Column(name = "ISSUE_SAMPLE_RECEIVE")
  public String issueSampleReceive;
  @Column(name = "ISSUE_QTY")
  public String issueQty;
  @Column(name = "ISSUE_REMARK")
  public String issueRemark;
  @Column(name = "ACTION_FLAG")
  public String actionFlag;
  @Column(name = "PRE_CORR_ACTION")
  public String preCorrAction;
  @Column(name = "ROOT_CAUSE")
  public String rootCause;
  @Column(name = "CORRECTIVE_ACTION")
  public String correctiveAction;
  @Column(name = "VERIFY_CORR_ACTION")
  public String verifyCorrAction;
  @Column(name = "PREVENT_RECURRENCE")
  public String preventRecurrence;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "FB_ISSUE_DATE")
  public String fbIssueDate;
  @Column(name = "FB_RETURN_DATE")
  public String fbReturnDate;
  @Column(name = "FB_COMMENT")
  public String fbComment;
  @Column(name = "ARBITRARILY_FLAG")
  public String arbitrarilyFlag;
  @Column(name = "SEVERITY")
  public String severity;
  @Column(name = "ANALYSIS_REMARKS")
  public String analysisRemarks;
  @Column(name = "REPORT_DATE")
  public String reportDate;
  @Column(name = "PROD_LEVEL")
  public String prodLevel;
  @Column(name = "ISSUE_STEP")
  public String issueStep;
  @Column(name = "PROD_STATUS")
  public String prodStatus;
  @Column(name = "DEFECT_RATE")
  public String defectRate;
  @Column(name = "CAUSE")
  public String cause;
  @Column(name = "FREQUENCY")
  public String frequency;
}
