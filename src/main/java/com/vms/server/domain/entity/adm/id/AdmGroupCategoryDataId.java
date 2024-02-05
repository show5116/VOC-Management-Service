package com.vms.server.domain.entity.adm.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AdmGroupCategoryDataId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String plant;
  public String groupTarget;
  public String groupIndex;
  public String groupCategory;
  public String groupObject;
  public String groupValue;
}
