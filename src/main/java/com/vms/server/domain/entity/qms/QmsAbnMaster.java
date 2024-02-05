package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsAbnMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsAbnMasterId.class)
@Table(name = "QMS_ABN_MASTER")
public class QmsAbnMaster {
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
  @Column(name = "ISSUE_PROCESS")
  public String issueProcess;
  @Column(name = "ISSUE_SUPPLIER")
  public String issueSupplier;
  @Column(name = "FAULTY_LOT_NUMBER")
  public String faultyLotNumber;
  @Column(name = "YIELD")
  public String yield;
  @Column(name = "ENGINEER_COMMENT")
  public String engineerComment;
  @Column(name = "RETURN_REMARK")
  public String returnRemark;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "ISSUE_DEVICE")
  public String issueDevice;
  @Column(name = "REVIEW_RESULT")
  public String reviewResult;
  @Column(name = "CAUSE_CATEGORY")
  public String causeCategory;
  @Column(name = "LOT_PROCESS")
  public String lotProcess;
}
