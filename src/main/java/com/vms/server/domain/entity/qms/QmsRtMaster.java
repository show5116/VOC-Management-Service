package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRtMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRtMasterId.class)
@Table(name = "QMS_RT_MASTER")
public class QmsRtMaster {
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
  @Column(name = "REQ_STEP")
  public String reqStep;
  @Column(name = "CURRENT_STEP")
  public String currentStep;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "MVT_VER")
  public String mvtVer;
  @Column(name = "USE_TEMP")
  public String useTemp;
  @Column(name = "SAMPLE_LEVEL")
  public String sampleLevel;
  @Column(name = "TEST_PURPOSE")
  public String testPurpose;
  @Column(name = "RMA_NUMBER")
  public String rmaNumber;
  @Column(name = "REQ_REASON")
  public String reqReason;
  @Column(name = "TEST_YIELD")
  public String testYield;
  @Column(name = "SPEC_MEETING")
  public String specMeeting;
  @Column(name = "PKG_TYPE")
  public String pkgType;
  @Column(name = "REQ_COMMENT")
  public String reqComment;
  @Column(name = "RESULT_COMMENT")
  public String resultComment;
  @Column(name = "FINAL_COMMENT")
  public String finalComment;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "ARBITRARILY_FLAG")
  public String arbitrarilyFlag;
  @Column(name = "W_COMMENT")
  public String wComment;
}
