package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPcosHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPcosHistoryId.class)
@Table(name = "QMS_PCOS_HISTORY")
public class QmsPcosHistory {
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
  @Column(name = "CUSTOMER")
  public String customer;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "PROGRAM_ID")
  public String programId;
  @Column(name = "OPERATION")
  public String operation;
  @Column(name = "DEPLOY_DEPT")
  public String deployDept;
  @Column(name = "DEPLOY_DATE")
  public String deployDate;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "REGULAR_FLAG")
  public String regularFlag;
  @Column(name = "MAGIC_FLAG")
  public String magicFlag;
}
