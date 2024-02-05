package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsCrsHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsCrsHistoryId.class)
@Table(name = "QMS_CRS_HISTORY")
public class QmsCrsHistory {
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
  @Column(name = "CONTRACT_TYPE")
  public String contractType;
  @Column(name = "CONTRACT_TARGET")
  public String contractTarget;
  @Column(name = "CRS_TITLE")
  public String crsTitle;
  @Column(name = "CRS_DRAFTER")
  public String crsDrafter;
  @Column(name = "CRS_PURPOSE")
  public String crsPurpose;
  @Column(name = "CRS_ARTICLES")
  public String crsArticles;
  @Column(name = "CRS_RISK")
  public String crsRisk;
  @Column(name = "CRS_COMMENT")
  public String crsComment;
  @Column(name = "DROP_FLAG")
  public String dropFlag;
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
  @Column(name = "CURRENT_STEP")
  public String currentStep;
}
