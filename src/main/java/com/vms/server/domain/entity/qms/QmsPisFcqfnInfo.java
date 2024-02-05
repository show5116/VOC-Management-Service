package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPisFcqfnInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPisFcqfnInfoId.class)
@Table(name = "QMS_PIS_FCQFN_INFO")
public class QmsPisFcqfnInfo {
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
  @Column(name = "BUMP_TYPE")
  public String bumpType;
  @Column(name = "BUMP_DIAMETER")
  public String bumpDiameter;
  @Column(name = "BUMP_HEIGHT")
  public String bumpHeight;
  @Column(name = "BUMP_QTY")
  public String bumpQty;
  @Column(name = "NET_DIE")
  public String netDie;
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
  @Column(name = "EXPOSED_PAD_SIZE_1")
  public String exposedPadSize1;
  @Column(name = "EXPOSED_PAD_SIZE_2")
  public String exposedPadSize2;
  @Column(name = "LAND_LENGTH_1")
  public String landLength1;
  @Column(name = "LAND_WIDTH_1")
  public String landWidth1;
  @Column(name = "LAND_LENGTH_2")
  public String landLength2;
  @Column(name = "LAND_WIDTH_2")
  public String landWidth2;
  @Column(name = "EXTRA_SUPPLIER_1")
  public String extraSupplier1;
  @Column(name = "EXTRA_SUPPLIER_2")
  public String extraSupplier2;
  @Column(name = "PKG_REMARK")
  public String pkgRemark;
  @Column(name = "SUPPLY_REMARK")
  public String supplyRemark;
}
