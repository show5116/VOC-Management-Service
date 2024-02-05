package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsMrbLotListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsMrbLotListId.class)
@Table(name = "QMS_MRB_LOT_LIST")
public class QmsMrbLotList {
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
  @Column(name = "LOT_NUMBER")
  public String lotNumber;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "WAFER_QTY")
  public String waferQty;
  @Column(name = "PCS_QTY")
  public String pcsQty;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "PKG_TYPE")
  public String pkgType;
  @Column(name = "PKG_SIZE")
  public String pkgSize;
  @Column(name = "PKG_LOT")
  public String pkgLot;
  @Column(name = "FAILL_RATE")
  public String faillRate;

  public QmsMrbLotList(String plant, String systemName, String qmsNumber, String revisionNo, String lotNumber, String device, String waferQty, String pcsQty, String remark, String pkgType, String pkgSize, String pkgLot, String faillRate) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.lotNumber = lotNumber;
    this.device = device;
    this.waferQty = waferQty;
    this.pcsQty = pcsQty;
    this.remark = remark;
    this.pkgType = pkgType;
    this.pkgSize = pkgSize;
    this.pkgLot = pkgLot;
    this.faillRate = faillRate;
  }
}
