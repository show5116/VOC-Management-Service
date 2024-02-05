package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPisQfnInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPisQfnInfoId.class)
@Table(name = "QMS_PIS_QFN_INFO")
public class QmsPisQfnInfo {
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
  @Column(name = "PKG_TYPE")
  public String pkgType;
  @Column(name = "PKG_TYPE_DETAIL")
  public String pkgTypeDetail;
  @Column(name = "PKG_SIZE_1")
  public String pkgSize1;
  @Column(name = "PKG_SIZE_2")
  public String pkgSize2;
  @Column(name = "PKG_SIZE_3")
  public String pkgSize3;
  @Column(name = "LEAD_COUNT")
  public String leadCount;
  @Column(name = "MSL_LEVEL")
  public String mslLevel;
  @Column(name = "LEAD_FRAME_TYPE")
  public String leadFrameType;
  @Column(name = "LEAD_FRAME_THICK")
  public String leadFrameThick;
  @Column(name = "PADDLE_SIZE_1")
  public String paddleSize1;
  @Column(name = "PADDLE_SIZE_2")
  public String paddleSize2;
  @Column(name = "EXPODE_PAD_SIZE_1")
  public String expodePadSize1;
  @Column(name = "EXPODE_PAD_SIZE_2")
  public String expodePadSize2;
  @Column(name = "LAND_LENGTH_1")
  public String landLength1;
  @Column(name = "LAND_WIDTH_1")
  public String landWidth1;
  @Column(name = "LAND_LENGTH_2")
  public String landLength2;
  @Column(name = "LAND_WIDTH_2")
  public String landWidth2;
  @Column(name = "EPOXY_TYPE")
  public String epoxyType;
  @Column(name = "WIRE_1")
  public String wire1;
  @Column(name = "WIRE_2")
  public String wire2;
  @Column(name = "DOWN_BONDING")
  public String downBonding;
  @Column(name = "REFLOW_REQUIRED")
  public String reflowRequired;
  @Column(name = "EXTRA_SUPPLIER_1")
  public String extraSupplier1;
  @Column(name = "PKG_REMARK")
  public String pkgRemark;
  @Column(name = "EXTRA_SUPPLIER_2")
  public String extraSupplier2;
  @Column(name = "SUPPLY_REMARK")
  public String supplyRemark;
}
