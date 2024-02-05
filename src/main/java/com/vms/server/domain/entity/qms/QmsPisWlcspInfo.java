package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPisWlcspInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPisWlcspInfoId.class)
@Table(name = "QMS_PIS_WLCSP_INFO")
public class QmsPisWlcspInfo {
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
  @Column(name = "PKG_SIZE_1")
  public String pkgSize1;
  @Column(name = "PKG_SIZE_2")
  public String pkgSize2;
  @Column(name = "PKG_SIZE_3")
  public String pkgSize3;
  @Column(name = "BALL_ARRAY_1")
  public String ballArray1;
  @Column(name = "BALL_ARRAY_2")
  public String ballArray2;
  @Column(name = "BALL_ARRAY_3")
  public String ballArray3;
  @Column(name = "MSL_LEVEL")
  public String mslLevel;
  @Column(name = "NC_BALL")
  public String ncBall;
  @Column(name = "BALL_DIAMETER")
  public String ballDiameter;
  @Column(name = "BALL_LEIGHT")
  public String ballLeight;
  @Column(name = "BSC_THICKNESS")
  public String bscThickness;
  @Column(name = "NET_DIE")
  public String netDie;
  @Column(name = "BALL_EDGE_DISTANCE")
  public String ballEdgeDistance;
  @Column(name = "OUTER_SEALRING_DISTANCE")
  public String outerSealringDistance;
  @Column(name = "PKG_REMARK")
  public String pkgRemark;
  @Column(name = "SUPPLY_REMARK")
  public String supplyRemark;
  @Column(name = "BALL_ARRAY_4")
  public String ballArray4;
}
