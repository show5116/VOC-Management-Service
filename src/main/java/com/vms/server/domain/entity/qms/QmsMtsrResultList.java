package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsMtsrResultListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsMtsrResultListId.class)
@Table(name = "QMS_MTSR_RESULT_LIST")
public class QmsMtsrResultList {
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
  @Column(name = "SUBCONTRACT")
  public String subcontract;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "MVT_VER")
  public String mvtVer;
  @Column(name = "FAB_CODE")
  public String fabCode;
  @Column(name = "OPERATION")
  public String operation;
  @Column(name = "OPER_DETAIL")
  public String operDetail;
  @Column(name = "LOT_NUMBER")
  public String lotNumber;
  @Column(name = "QTY")
  public String qty;
  @Column(name = "WAFER_QTY")
  public String waferQty;
  @Column(name = "WAFER_ID")
  public String waferId;
}
