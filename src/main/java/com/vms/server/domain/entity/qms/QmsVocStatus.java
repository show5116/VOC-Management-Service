package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsVocStatusId;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderMethodName = "innerBuilder")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QMS_VOC_STATUS")
public class QmsVocStatus {
  @Id
  @EmbeddedId
  private QmsVocStatusId id;
  @Column(name = "ISSUE_DATE")
  @CreatedDate
  private String issueDate; // 요청일
  @Column(name = "CUSTOMER")
  @CreatedBy
  private String customer; // 등록자
  @Column(name = "RECEPTION_DEPT")
  private String receptionDept;
  @Column(name = "CLASSIFICATION")
  private String classification; // 요청 종류
  @Column(name = "REQUIRED_RESPONSE_DATE")
  private String requiredResponseDate; // 납기 요청일
  @Column(name = "REPONSE_DATE")
  private String reponseDate;
  @Column(name = "REMARK")
  private String remark; // 중요도
  @Column(name = "ADD_ISSUE_FLAG")
  private String addIssueFlag;
  @Column(name = "CLOSED_FLAG")
  private String closedFlag; // 완료 여부
  @Column(name = "REG_DATE")
  private String regDate;
  @Column(name = "REG_USER")
  private String regUser;
  @Column(name = "CLOSED_DATE")
  private String closedDate; // 완료일
  @Column(name = "CLOSED_USER")
  private String closedUser; // 완료자
  @Column(name = "PERSON_IN_CHARGE")
  private String personInCharge; // 담당자
  @Column(name = "REQUIREMENT")
  private String requirement; // 내용
  @Column(name = "DEVICE")
  private String device;
  @Column(name = "UPDATE_DATE")
  @LastModifiedDate
  private String updateDate; // 수정일
  @Column(name = "UPDATE_USER")
  @LastModifiedBy
  private String updateUser; // 수정자

  @OneToMany
  @JoinColumns({
          @JoinColumn(name = "plant", referencedColumnName = "plant"),
          @JoinColumn(name = "system_name", referencedColumnName = "system_name"),
          @JoinColumn(name = "qms_number", referencedColumnName = "qms_number"),
          @JoinColumn(name = "revision_no", referencedColumnName = "revision_no")
  })
  private List<QmsAttachFile> attachFiles;

  @PrePersist
  public void prePersist() {
    String customLocalDateTimeFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    this.issueDate = customLocalDateTimeFormat;
    this.updateDate = customLocalDateTimeFormat;
  }

  @PreUpdate
  public void preUpdate() {
    String customLocalDateTimeFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    this.updateDate = customLocalDateTimeFormat;
  }

  public QmsVocHistory toHistory() {
    return new QmsVocHistory(this);
  }

  public static QmsVocStatusBuilder builder(QmsVocStatusId id) {
    return innerBuilder().id(id);
  }
}
