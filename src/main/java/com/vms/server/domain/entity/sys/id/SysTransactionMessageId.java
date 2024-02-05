package com.vms.server.domain.entity.sys.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysTransactionMessageId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String industrialType;
  public String moduleId;
  public String transaction;
  public String messageId;
}
