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
  public String plant; // plant
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName; // service
  @Id
  @Column(name = "QMS_NUMBER")
  public String qmsNumber; // 문서 번호
  @Id
  @Column(name = "REVISION_NO")
  public String revisionNo; // history 필요한가?
  @Column(name = "ISSUE_DATE")
  public String issueDate; // 요청일
  @Column(name = "CUSTOMER")
  public String customer; // 등록자
  @Column(name = "RECEPTION_DEPT")
  public String receptionDept;
  @Column(name = "CLASSIFICATION")
  public String classification; // 요청 종류
  @Column(name = "REQUIRED_RESPONSE_DATE")
  public String requiredResponseDate; // 납기 요청일
  @Column(name = "REPONSE_DATE")
  public String reponseDate;
  @Column(name = "REMARK")
  public String remark; // 중요도
  @Column(name = "ADD_ISSUE_FLAG")
  public String addIssueFlag;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag; // 완료 여부
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "CLOSED_DATE")
  public String closedDate; // 완료일
  @Column(name = "CLOSED_USER")
  public String closedUser; // 완료자
  @Column(name = "PERSON_IN_CHARGE")
  public String personInCharge; // 담당자
  @Column(name = "REQUIREMENT")
  public String requirement; // 내용
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "UPDATE_DATE")
  public String updateDate; // 수정일
  @Column(name = "UPDATE_USER")
  public String updateUser; // 수정자
}
