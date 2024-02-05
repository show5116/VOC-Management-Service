package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRtItemSelectedId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRtItemSelectedId.class)
@Table(name = "QMS_RT_ITEM_SELECTED")
public class QmsRtItemSelected {
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
  @Column(name = "TEST_TYPE")
  public String testType;
  @Id
  @Column(name = "STRESS")
  public String stress;
  @Column(name = "TEST_FLAG")
  public String testFlag;
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
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
}
