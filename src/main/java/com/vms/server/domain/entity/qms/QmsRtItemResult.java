package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRtItemResultId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRtItemResultId.class)
@Table(name = "QMS_RT_ITEM_RESULT")
public class QmsRtItemResult {
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
  @Id
  @Column(name = "LOT_NUMBER")
  public String lotNumber;
  @Id
  @Column(name = "DURATION")
  public String duration;
  @Column(name = "TEST_CONDITIONS")
  public String testConditions;
  @Column(name = "START_DATE")
  public String startDate;
  @Column(name = "FT_COMP_DATE")
  public String ftCompDate;
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
  @Column(name = "STOP_DATE")
  public String stopDate;
}
