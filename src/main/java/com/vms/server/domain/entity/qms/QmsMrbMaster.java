package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsMrbMasterId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsMrbMasterId.class)
@Table(name = "QMS_MRB_MASTER")
public class QmsMrbMaster {
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
  @Column(name = "ISSUE_TIME")
  public String issueTime;
  @Column(name = "ISSUE_TITLE")
  public String issueTitle;
  @Column(name = "ISSUE_PROCESS")
  public String issueProcess;
  @Column(name = "ISSUE_SUPPLIER")
  public String issueSupplier;
  @Column(name = "FAULTY_PHENOMENON")
  public String faultyPhenomenon;
  @Column(name = "FAULTY_CAUSE")
  public String faultyCause;
  @Column(name = "MEASURES")
  public String measures;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "PROBLEM_DISPOSIOTION")
  public String problemDisposiotion;
  @Column(name = "EXTERNAL_FLAG")
  public String externalFlag;
  @Column(name = "AVAILABILITY_FLAG")
  public String availabilityFlag;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "ROOT_CAUSE")
  public String rootCause;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "CLOSED_DATE")
  public String closedDate;
  @Column(name = "CLOSED_USER")
  public String closedUser;
  @Column(name = "CURRENT_STEP")
  public String currentStep;
  @Column(name = "LOT_DISPOSIOTION")
  public String lotDisposiotion;
  @Column(name = "UNNECESSARY_REASON")
  public String unnecessaryReason;

  public void updateClosedDateAndUser(String closedDate,String userId){
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//    LocalDateTime now = LocalDateTime.now();
//    String formattedDate = now.format(formatter);
    this.closedDate = closedDate;
    this.closedUser = userId;

  }
  public void updateUpdateRegDateAndUser( String userId){
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//    LocalDateTime now = LocalDateTime.now();
//    String formattedDate = now.format(formatter);
//    this.updateDate = formattedDate;
//    this.updateUser = userId;

  }

  public void changeCurrentStep(String currentStep){
    this.currentStep = currentStep;
  }
  public void changeClosedFlag(String closedFlag){
    this.closedFlag =closedFlag;
  }

  public QmsMrbMaster(String plant, String systemName, String qmsNumber, String revisionNo, String issueTime, String issueTitle, String issueProcess, String issueSupplier, String faultyPhenomenon, String faultyCause, String measures, String device, String problemDisposiotion, String externalFlag, String availabilityFlag, String closedFlag, String rootCause, String regDate, String regUser, String closedDate, String closedUser, String currentStep, String lotDisposiotion, String unnecessaryReason) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.issueTime = issueTime;
    this.issueTitle = issueTitle;
    this.issueProcess = issueProcess;
    this.issueSupplier = issueSupplier;
    this.faultyPhenomenon = faultyPhenomenon;
    this.faultyCause = faultyCause;
    this.measures = measures;
    this.device = device;
    this.problemDisposiotion = problemDisposiotion;
    this.externalFlag = externalFlag;
    this.availabilityFlag = availabilityFlag;
    this.closedFlag = closedFlag;
    this.rootCause = rootCause;
    this.regDate = regDate;
    this.regUser = regUser;
    this.closedDate = closedDate;
    this.closedUser = closedUser;
    this.currentStep = currentStep;
    this.lotDisposiotion = lotDisposiotion;
    this.unnecessaryReason = unnecessaryReason;
  }

  public void updateCurrentStep(String step){
    this.currentStep = step;
  }

  public void updateRegUser(String changeUser){
    this.regUser = changeUser;
  }
}
