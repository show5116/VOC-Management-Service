package com.vms.server.domain.entity.sys;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysClientId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String computerName;
  public String useProgram;
  public String loginTime;
}
