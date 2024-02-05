package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsLsMasterId;
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
@IdClass(QmsLsMasterId.class)
@Table(name = "QMS_LS_MASTER")
public class QmsLsMaster {
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
  @Column(name = "ISSUE_TITLE")
  public String issueTitle;
  @Column(name = "DIVISION")
  public String division;
  @Column(name = "DEPARTMENT")
  public String department;
  @Column(name = "ISSUE_CATEGORY")
  public String issueCategory;
  @Column(name = "PROCESS")
  public String process;
  @Column(name = "SUPPLIER")
  public String supplier;
  @Column(name = "CAUSE")
  public String cause;
  @Column(name = "ISSUE_SUMMARY")
  public String issueSummary;
  @Column(name = "CAUSE_COMMENT")
  public String causeComment;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "CLOSED_FLAG")
  public String closedFlag;
  @Column(name = "CLOSED_DATE")
  public String closedDate;
  @Column(name = "CLOSED_USER")
  public String closedUser;
  @Column(name = "MEASURES")
  public String measures;

  public void updateRegDateAndUser(String userId){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    LocalDateTime now = LocalDateTime.now();
    String formattedDate = now.format(formatter);

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
