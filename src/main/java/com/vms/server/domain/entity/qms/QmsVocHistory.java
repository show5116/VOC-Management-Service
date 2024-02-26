package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsVocHistoryId;
import com.vms.server.domain.entity.qms.id.QmsVocStatusId;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "QMS_VOC_HISTORY")
public class QmsVocHistory {
  @Id
  @EmbeddedId
  public QmsVocHistoryId id;
  @Column(name = "ISSUE_DATE")
  public String issueDate;
  @Column(name = "CUSTOMER")
  public String customer;
  @Column(name = "RECEPTION_DEPT")
  public String receptionDept;
  @Column(name = "CLASSIFICATION")
  public String classification;
  @Column(name = "REQUIRED_RESPONSE_DATE")
  public String requiredResponseDate;
  @Column(name = "REPONSE_DATE")
  public String reponseDate;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "ADD_ISSUE_FLAG")
  public String addIssueFlag;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "CLOSED_DATE")
  public String closedDate;
  @Column(name = "CLOSED_USER")
  public String closedUser;
  @Column(name = "PERSON_IN_CHARGE")
  public String personInCharge;
  @Column(name = "REQUIREMENT")
  public String requirement;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;

  public QmsVocHistory(QmsVocStatus qmsVocStatus) {
    QmsVocStatusId statusId = qmsVocStatus.getId();
    this.id = new QmsVocHistoryId(statusId.getPlant(), statusId.getSystemName(),
            statusId.getQmsNumber(), statusId.getRevisionNo(), qmsVocStatus.getUpdateDate());
    this.issueDate = qmsVocStatus.getIssueDate();
    this.customer = qmsVocStatus.getCustomer();
    this.receptionDept = qmsVocStatus.getReceptionDept();
    this.classification = qmsVocStatus.getClassification();
    this.requiredResponseDate = qmsVocStatus.getRequiredResponseDate();
    this.reponseDate = qmsVocStatus.getReponseDate();
    this.remark = qmsVocStatus.getRemark();
    this.addIssueFlag = qmsVocStatus.getAddIssueFlag();
    this.closedFlag = qmsVocStatus.getClosedFlag();
    this.regDate = qmsVocStatus.getRegDate();
    this.regUser = qmsVocStatus.getRegUser();
    this.closedDate = qmsVocStatus.getClosedDate();
    this.closedUser = qmsVocStatus.getClosedUser();
    this.personInCharge = qmsVocStatus.getPersonInCharge();
    this.requirement = qmsVocStatus.getRequirement();
    this.device = qmsVocStatus.getDevice();
    this.updateDate = qmsVocStatus.getUpdateDate();
    this.updateUser = qmsVocStatus.getUpdateUser();
  }
}
