package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRtItemListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRtItemListId.class)
@Table(name = "QMS_RT_ITEM_LIST")
public class QmsRtItemList {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "SEQ")
  public String seq;
  @Column(name = "TEST_TYPE")
  public String testType;
  @Column(name = "STRESS")
  public String stress;
  @Column(name = "TEST_CONDITIONS")
  public String testConditions;
  @Column(name = "DURATION")
  public String duration;
  @Column(name = "DURATION_UNIT")
  public String durationUnit;
  @Column(name = "LOT_CNT")
  public String lotCnt;
  @Column(name = "SS_PER_LOT")
  public String ssPerLot;
  @Column(name = "MODIFICATION")
  public String modification;
  @Column(name = "REFERENCE")
  public String reference;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "DURATION_MIN")
  public String durationMin;
  @Column(name = "DURATION_MAX")
  public String durationMax;
}
