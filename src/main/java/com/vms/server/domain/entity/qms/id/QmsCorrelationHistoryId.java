package com.vms.server.domain.entity.qms.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QmsCorrelationHistoryId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String site;
  public String device;
  public String equipmentId;
  public String pcbId;
  public String transTime;
}
