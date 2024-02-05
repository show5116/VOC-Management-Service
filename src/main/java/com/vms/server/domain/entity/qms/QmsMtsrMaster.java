package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsMtsrMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsMtsrMasterId.class)
@Table(name = "QMS_MTSR_MASTER")
public class QmsMtsrMaster {
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
  @Column(name = "REQ_REASON")
  public String reqReason;
  @Column(name = "MANAGER")
  public String manager;
  @Column(name = "SUBCONTRACT")
  public String subcontract;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "OPERATION")
  public String operation;
  @Column(name = "REQUEST_REMARK")
  public String requestRemark;
  @Column(name = "RESULT_REMARK")
  public String resultRemark;
  @Column(name = "REQ_MAIL_CNT")
  public String reqMailCnt;
  @Column(name = "RES_MAIL_CNT")
  public String resMailCnt;
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
  @Column(name = "CLOSED_DATE")
  public String closedDate;
  @Column(name = "CLOSED_USER")
  public String closedUser;
}
