package com.vms.server.domain.entity.adm.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AdmEquipmentConfId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String equipmentId;
  public String site;
  public String serialNo;
  public String resourceId;
  public String slotNo;
  public String resSerialNo;
  public String slotType;
}
