package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPcnStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPcnStatusId.class)
@Table(name = "QMS_PCN_STATUS")
public class QmsPcnStatus {
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
  @Column(name = "CUSTOMER")
  public String customer;
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
  @Id
  @Column(name = "ECN_NUMBER")
  public String ecnNumber;
  @Column(name = "ISSUE_DATE")
  public String issueDate;
}
