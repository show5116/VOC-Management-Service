package com.vms.server.domain.entity.sys.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysClientHistoryId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String computerName;
  public String useProgram;
  public String loginTime;
}
