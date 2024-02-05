package com.vms.server.domain.entity.sys.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysEventLogId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String userId;
  public String useProgram;
  public String menuDescription;
  public String eventDescription;
  public String eventTime;
}
