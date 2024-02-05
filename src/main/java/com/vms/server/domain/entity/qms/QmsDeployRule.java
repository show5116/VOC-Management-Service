package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsDeployRuleId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsDeployRuleId.class)
@Table(name = "QMS_DEPLOY_RULE")
public class QmsDeployRule {
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
  @Column(name = "SEQ_NO")
  public String seqNo;
  @Column(name = "DEPLOY_TYPE")
  public String deployType;
  @Column(name = "DEPT_ID")
  public String deptId;
  @Column(name = "USER_ID")
  public String userId;
}
