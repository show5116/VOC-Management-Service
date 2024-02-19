package com.vms.server.domain.entity.qms.id;

import java.io.Serializable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@Embeddable
public class QmsVocStatusId implements Serializable {
  @Column(name = "plant")
  private String plant;
  @Column(name = "system_name")
  private String systemName;
  @Column(name = "qms_number")
  private String qmsNumber;
  @Column(name = "revision_no")
  private String revisionNo;

  public QmsVocStatusId(String plant, String systemName, String qmsNumber, String revisionNo) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
  }
}
