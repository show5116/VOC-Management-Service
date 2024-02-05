package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsApprovalRuleEntrustId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsApprovalRuleEntrustId.class)
@Table(name = "QMS_APPROVAL_RULE_ENTRUST")
public class QmsApprovalRuleEntrust {
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
  @Column(name = "APPROVAL_TYPE")
  public String approvalType;
  @Column(name = "APPROVAL_DATE")
  public String approvalDate;
  @Column(name = "APPROVAL_FLAG")
  public String approvalFlag;
  @Id
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "COMMENTS")
  public String comments;
  @Id
  @Column(name = "RULE_TYPE")
  public String ruleType;
  @Id
  @Column(name = "SEQ")
  public String seq;
}
