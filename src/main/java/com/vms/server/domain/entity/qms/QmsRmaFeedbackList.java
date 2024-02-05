package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRmaFeedbackListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRmaFeedbackListId.class)
@Table(name = "QMS_RMA_FEEDBACK_LIST")
public class QmsRmaFeedbackList {
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
  @Column(name = "FEEDBACK_NO")
  public String feedbackNo;
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "FB_ISSUE_DATE")
  public String fbIssueDate;
  @Column(name = "FB_RETURN_DATE")
  public String fbReturnDate;
  @Column(name = "FB_REMARK")
  public String fbRemark;
}
