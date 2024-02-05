package com.vms.server.domain.entity.sys.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysMenuStructureId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String groupId;
  public String moduleId;
  public String menuId;
}
