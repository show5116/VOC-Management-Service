package com.vms.server.domain.entity.aem.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AemEquipmentPmDtlStatusId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String equipmentId;
  public String site;
  public String transTime;
  public String pmItem;
  public String planDate;
  public String resourceId;
  public String slotNo;
  public String resSerialNo;
}
