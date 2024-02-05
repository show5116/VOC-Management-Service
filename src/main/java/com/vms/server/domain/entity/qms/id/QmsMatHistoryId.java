package com.vms.server.domain.entity.qms.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QmsMatHistoryId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String matLotNumber;
  public String sysTransTime;
  public String lotHstSeq;
}
