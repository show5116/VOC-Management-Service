package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsDocumentHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsDocumentHistoryId.class)
@Table(name = "QMS_DOCUMENT_HISTORY")
public class QmsDocumentHistory {
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
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "CURRENT_STEP")
  public String currentStep;
  @Column(name = "DOC_TYPE")
  public String docType;
  @Column(name = "WORK_TYPE")
  public String workType;
  @Column(name = "DOC_TITLE")
  public String docTitle;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "EXPIRATION_DATE")
  public String expirationDate;
  @Column(name = "EXPIRE_FLAG")
  public String expireFlag;
  @Column(name = "RETURN_REMARK")
  public String returnRemark;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "VALIDATION_FLAG")
  public String validationFlag;
  @Column(name = "PRENUM_FLAG")
  public String prenumFlag;
}
