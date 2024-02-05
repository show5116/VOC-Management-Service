package com.vms.server.domain.entity.sys.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysFileLogId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String userId;
  public String transTime;
  public String ipAddr;
  public String fileName;
}
