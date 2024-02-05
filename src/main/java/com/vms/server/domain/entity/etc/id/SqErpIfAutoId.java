package com.vms.server.domain.entity.etc.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SqErpIfAutoId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String tblNm;
  public String transTime;
}
