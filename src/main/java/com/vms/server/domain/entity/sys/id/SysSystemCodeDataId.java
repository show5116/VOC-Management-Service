package com.vms.server.domain.entity.sys.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SysSystemCodeDataId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String tableName;
  public String codeName;
  public String codeSeq;
}
