package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsQrReliabilityId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsQrReliabilityId.class)
@Table(name = "QMS_QR_RELIABILITY")
public class QmsQrReliability {
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
  @Column(name = "PKG_TYPE")
  public String pkgType;
  @Id
  @Column(name = "ITEM_CODE")
  public String itemCode;
  @Id
  @Column(name = "ITEM_SEQ_NO")
  public String itemSeqNo;
  @Column(name = "ITEM_STANDARD")
  public String itemStandard;
  @Column(name = "ITEM_CONDITION")
  public String itemCondition;
  @Column(name = "ITEM_DURATION")
  public String itemDuration;
  @Column(name = "ITEM_SAMPLE")
  public String itemSample;
  @Column(name = "ITEM_JUDGEMENT")
  public String itemJudgement;
}
