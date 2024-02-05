package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRmaClaimMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRmaClaimMasterId.class)
@Table(name = "QMS_RMA_CLAIM_MASTER")
public class QmsRmaClaimMaster {
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
  @Column(name = "SCRAP_COST")
  public String scrapCost;
  @Column(name = "SCRAP_QTY")
  public String scrapQty;
  @Column(name = "SHIPBACK_COST")
  public String shipbackCost;
  @Column(name = "SHIPBACK_QTY")
  public String shipbackQty;
  @Column(name = "CUSTOMER_COST")
  public String customerCost;
  @Column(name = "MONEY_UNIT")
  public String moneyUnit;
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
  @Column(name = "OTHERS_COST")
  public String othersCost;
}
