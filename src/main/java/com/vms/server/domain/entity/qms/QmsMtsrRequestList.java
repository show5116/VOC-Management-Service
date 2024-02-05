package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsMtsrRequestListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsMtsrRequestListId.class)
@Table(name = "QMS_MTSR_REQUEST_LIST")
public class QmsMtsrRequestList {
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
  @Column(name = "MVT_VER")
  public String mvtVer;
  @Column(name = "MVT_FLAG")
  public String mvtFlag;
  @Column(name = "QTY")
  public String qty;
  @Column(name = "QTY_UNIT")
  public String qtyUnit;
  @Column(name = "OPER_DETAIL")
  public String operDetail;
  @Column(name = "OPER_FLAG")
  public String operFlag;
  @Column(name = "LOT_NUMBER")
  public String lotNumber;
  @Column(name = "LOT_FLAG")
  public String lotFlag;
  @Column(name = "WAFER_ID")
  public String waferId;
  @Column(name = "WAFER_FLAG")
  public String waferFlag;
}
