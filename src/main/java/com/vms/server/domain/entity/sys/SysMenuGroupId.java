package com.vms.server.domain.entity.sys;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysMenuGroupId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String moduleId;
  public String groupId;
}
