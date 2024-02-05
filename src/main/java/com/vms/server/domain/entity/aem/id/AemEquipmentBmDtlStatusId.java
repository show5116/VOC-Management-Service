package com.vms.server.domain.entity.aem.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AemEquipmentBmDtlStatusId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String equipmentId;
  public String site;
  public String transTime;
  public String bmStartDate;
  public String resourceId;
  public String slotNo;
  public String resSerialNo;
}
