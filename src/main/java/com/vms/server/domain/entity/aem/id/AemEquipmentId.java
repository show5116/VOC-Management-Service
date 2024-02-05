package com.vms.server.domain.entity.aem.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AemEquipmentId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String equipmentId;
}
