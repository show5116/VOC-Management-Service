package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPisCofInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPisCofInfoId.class)
@Table(name = "QMS_PIS_COF_INFO")
public class QmsPisCofInfo {
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
  @Column(name = "MIN_BUMP_SIZE_1")
  public String minBumpSize1;
  @Column(name = "MIN_BUMP_SIZE_2")
  public String minBumpSize2;
  @Column(name = "MIN_BUMP_SPACE")
  public String minBumpSpace;
  @Column(name = "MIN_BUMP_PITCH")
  public String minBumpPitch;
  @Column(name = "OVERLAP")
  public String overlap;
  @Column(name = "AU_USAGE")
  public String auUsage;
  @Column(name = "BUMP_HEIGHT")
  public String bumpHeight;
  @Column(name = "BUMP_QTY")
  public String bumpQty;
  @Column(name = "NET_DIE")
  public String netDie;
  @Column(name = "SI_THICK")
  public String siThick;
  @Column(name = "AL_SPEC")
  public String alSpec;
  @Column(name = "SUPPLY_REMARK")
  public String supplyRemark;
}
