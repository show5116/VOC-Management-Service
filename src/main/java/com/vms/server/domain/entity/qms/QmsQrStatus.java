package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsQrStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsQrStatusId.class)
@Table(name = "QMS_QR_STATUS")
public class QmsQrStatus {
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
  @Column(name = "QR_TITLE")
  public String qrTitle;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "PKG_TYPE")
  public String pkgType;
  @Column(name = "SUPPLY_CHAIN_COMMENT")
  public String supplyChainComment;
  @Column(name = "REMARK")
  public String remark;
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
  @Column(name = "REMARK_INT")
  public String remarkInt;
}
