package com.vms.server.domain.entity.etc.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EvaluationItemListId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String evaluationCode;
  public String changeType;
  public String changeItem;
  public String changeLevel;
}
