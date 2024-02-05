package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRfaStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRfaStatusId.class)
@Table(name = "QMS_RFA_STATUS")
public class QmsRfaStatus {
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
  @Column(name = "FA_PURPOSE")
  public String faPurpose;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "MVT_VER")
  public String mvtVer;
  @Column(name = "PRD_TYPE")
  public String prdType;
  @Column(name = "FA_SUBC")
  public String faSubc;
  @Column(name = "FA_MANAGER")
  public String faManager;
  @Column(name = "TOTAL_QTY")
  public String totalQty;
  @Column(name = "DEGREE")
  public String degree;
  @Column(name = "FAB_SUBC")
  public String fabSubc;
  @Column(name = "TECH_NODE")
  public String techNode;
  @Column(name = "CUSTOMER")
  public String customer;
  @Column(name = "RMA_NUMBER")
  public String rmaNumber;
  @Column(name = "REMARK")
  public String remark;
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
