package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsPisFilmInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsPisFilmInfoId.class)
@Table(name = "QMS_PIS_FILM_INFO")
public class QmsPisFilmInfo {
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
  @Column(name = "TYPE")
  public String type;
  @Column(name = "PKG_TYPE")
  public String pkgType;
  @Column(name = "COF_SPEC")
  public String cofSpec;
  @Column(name = "FILM_TYPE")
  public String filmType;
  @Column(name = "MIN_ILB_PITCH")
  public String minIlbPitch;
  @Column(name = "TAPE_MAT")
  public String tapeMat;
  @Column(name = "SN_PLAT_THICK_1")
  public String snPlatThick1;
  @Column(name = "SN_PLAT_THICK_2")
  public String snPlatThick2;
  @Column(name = "FEED_PITCH")
  public String feedPitch;
  @Column(name = "SOLDER_RES_THICK_1")
  public String solderResThick1;
  @Column(name = "SOLDER_RES_THICK_2")
  public String solderResThick2;
  @Column(name = "CHIP_SIZE_1")
  public String chipSize1;
  @Column(name = "CHIP_SIZE_2")
  public String chipSize2;
  @Column(name = "TAPE_THICK_1")
  public String tapeThick1;
  @Column(name = "TAPE_THICK_2")
  public String tapeThick2;
  @Column(name = "ALL_DIMENSION")
  public String allDimension;
  @Column(name = "CU_THICK_1")
  public String cuThick1;
  @Column(name = "CU_THICK_2")
  public String cuThick2;
  @Column(name = "TOTAL_LEAD_COUNT")
  public String totalLeadCount;
}
