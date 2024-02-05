package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPisDieInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPisDieInfoId.class)
@Table(name = "QMS_PIS_DIE_INFO")
public class QmsPisDieInfo {
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
  @Column(name = "DIE_TYPE")
  public String dieType;
  @Column(name = "FAB")
  public String fab;
  @Column(name = "TOP_METAL_THICKNESS")
  public String topMetalThickness;
  @Column(name = "DIE_SIZE_1")
  public String dieSize1;
  @Column(name = "DIE_SIZE_2")
  public String dieSize2;
  @Column(name = "NET_DIE")
  public String netDie;
  @Column(name = "TECH")
  public String tech;
  @Column(name = "BG_THICK")
  public String bgThick;
  @Column(name = "SL_SIZE_1")
  public String slSize1;
  @Column(name = "SL_SIZE_2")
  public String slSize2;
  @Column(name = "DOUBLE_SEAL_FLAG")
  public String doubleSealFlag;
  @Column(name = "TEG_PAD_MET_SIZE_1")
  public String tegPadMetSize1;
  @Column(name = "TEG_PAD_MET_SIZE_2")
  public String tegPadMetSize2;
  @Column(name = "TEG_PAD_OPEN_SIZE_1")
  public String tegPadOpenSize1;
  @Column(name = "TEG_PAD_OPEN_SIZE_2")
  public String tegPadOpenSize2;
  @Column(name = "PASSIVATION_THICKNESS")
  public String passivationThickness;
  @Column(name = "LAYER")
  public String layer;
}
