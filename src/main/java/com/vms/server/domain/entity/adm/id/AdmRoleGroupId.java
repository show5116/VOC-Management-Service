package com.vms.server.domain.entity.adm.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AdmRoleGroupId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String roleGroup;
}
