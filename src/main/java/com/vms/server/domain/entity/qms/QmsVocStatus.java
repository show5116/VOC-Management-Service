package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsVocStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsVocStatusId.class)
@Table(name = "QMS_VOC_STATUS")
public class QmsVocStatus {
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
  public String remark; // ?
  @Column(name = "ADD_ISSUE_FLAG")
  public String addIssueFlag;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag; // ?
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
}
