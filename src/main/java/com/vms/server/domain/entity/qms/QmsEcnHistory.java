package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsEcnHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsEcnHistoryId.class)
@Table(name = "QMS_ECN_HISTORY")
public class QmsEcnHistory {
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
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "CURRENT_STEP")
  public String currentStep;
  @Column(name = "ISSUE_TITLE")
  public String issueTitle;
  @Column(name = "CHANGE_PROPOSAL")
  public String changeProposal;
  @Column(name = "SUPPLIER")
  public String supplier;
  @Column(name = "CHANGE_GRADE")
  public String changeGrade;
  @Column(name = "CHANGE_TYPE")
  public String changeType;
  @Column(name = "CHANGE_ITEM")
  public String changeItem;
  @Column(name = "ISSUE_DATE")
  public String issueDate;
  @Column(name = "CHANGE_TARGET")
  public String changeTarget;
  @Column(name = "CHANGE_BEFORE")
  public String changeBefore;
  @Column(name = "CHANGE_AFTER")
  public String changeAfter;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "DROP_FLAG")
  public String dropFlag;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "ROHS_FLAG")
  public String rohsFlag;
  @Column(name = "REVIEW_RESULT")
  public String reviewResult;
}
