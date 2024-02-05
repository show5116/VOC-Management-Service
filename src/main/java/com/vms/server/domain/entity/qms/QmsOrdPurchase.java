package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsOrdPurchaseId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsOrdPurchaseId.class)
@Table(name = "QMS_ORD_PURCHASE")
public class QmsOrdPurchase {
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
  @Column(name = "SEQ")
  public String seq;
  @Column(name = "REQ_CONTENT")
  public String reqContent;
  @Column(name = "MAT_CATEGORY")
  public String matCategory;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "QTY")
  public String qty;
  @Column(name = "PRD_TYPE")
  public String prdType;
  @Column(name = "MAT_CODE")
  public String matCode;
  @Column(name = "SUBC_CODE")
  public String subcCode;
  @Column(name = "UNIT_PRICE")
  public String unitPrice;
  @Column(name = "VAT_YN")
  public String vatYn;
  @Column(name = "TOTAL_PRICE")
  public String totalPrice;
  @Column(name = "IMPORT_DATE")
  public String importDate;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "BIB_NO")
  public String bibNo;
  @Column(name = "UNIT_PRICE_USD")
  public String unitPriceUsd;
}
