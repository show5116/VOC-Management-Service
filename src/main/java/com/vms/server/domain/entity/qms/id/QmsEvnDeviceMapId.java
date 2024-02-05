package com.vms.server.domain.entity.qms.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QmsEvnDeviceMapId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String systemName;
  public String device;
  public String qmsNumber;
  public String revisionNo;
}
