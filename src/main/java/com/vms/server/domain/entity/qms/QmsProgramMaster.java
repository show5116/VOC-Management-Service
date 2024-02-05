package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsProgramMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsProgramMasterId.class)
@Table(name = "QMS_PROGRAM_MASTER")
public class QmsProgramMaster {
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
  @Column(name = "DEVICE")
  public String device;
  @Id
  @Column(name = "PROGRAM_ID")
  public String programId;
  @Id
  @Column(name = "CUSTOMER")
  public String customer;
  @Column(name = "TEST_TIME")
  public String testTime;
  @Column(name = "TEMPERATURE_2ND")
  public String temperature2Nd;
  @Column(name = "EXTERNAL_FLAG")
  public String externalFlag;
  @Column(name = "RELEASE_DATE")
  public String releaseDate;
  @Column(name = "MANAGER")
  public String manager;
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
  @Column(name = "TESTER_OPTION")
  public String testerOption;
  @Column(name = "MAIL_FLAG")
  public String mailFlag;
}
