package com.vms.server.domain.entity.etc.id;

import java.io.Serializable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class IsendmailAttachFileId implements Serializable {
  private static final long serialVersionUID = 1L;
  public String seq;
  public String systemName;
  public String fileSeq;
}
