package com.vms.server.domain.entity.qms.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QmsApprovalRuleId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String systemName;
  public String qmsNumber;
  public String revisionNo;
  public String approvalType;
  public String userId;
  public String ruleType;
}
