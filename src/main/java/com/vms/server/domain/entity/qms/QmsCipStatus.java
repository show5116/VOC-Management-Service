package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsCipStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsCipStatusId.class)
@Table(name = "QMS_CIP_STATUS")
public class QmsCipStatus {
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
  @Column(name = "PROJECT_TITLE")
  public String projectTitle;
  @Column(name = "SUPPLIER")
  public String supplier;
  @Column(name = "PROCESS")
  public String process;
  @Column(name = "DEVICE")
  public String device;
  @Column(name = "ISSUE_DATE")
  public String issueDate;
  @Column(name = "ESTIMATED_CMPL_DATE")
  public String estimatedCmplDate;
  @Column(name = "COMPLETE_DATE")
  public String completeDate;
  @Column(name = "IMPROVEMENT_ITEMS")
  public String improvementItems;
  @Column(name = "CURRENT_LEVEL")
  public String currentLevel;
  @Column(name = "TARGET_LEVEL")
  public String targetLevel;
  @Column(name = "IMPROVEMENT_RESULT")
  public String improvementResult;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;

  public void updateRegDateAndUser(String userId){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    LocalDateTime now = LocalDateTime.now();
    String formattedDate = now.format(formatter);
    this.updateDate = formattedDate;
    this.updateUser = userId;
    this.regDate = formattedDate;
    this.regUser = userId;
  }
  public void updateUpdateRegDateAndUser( String userId){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    LocalDateTime now = LocalDateTime.now();
    String formattedDate = now.format(formatter);
    this.updateDate = formattedDate;
    this.updateUser = userId;

  }
}
