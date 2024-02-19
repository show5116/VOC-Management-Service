package com.vms.server.domain.entity.qms.id;

import java.io.Serializable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@Embeddable
public class QmsVocHistoryId implements Serializable {
  @Column(name = "plant")
  public String plant;
  @Column(name = "system_name")
  public String systemName;
  @Column(name = "qms_number")
  public String qmsNumber;
  @Column(name = "revision_no")
  public String revisionNo;
  @Column(name = "trans_time")
  public String transTime;

  public QmsVocHistoryId(String plant, String systemName, String qmsNumber, String revisionNo, String transTime) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.transTime = transTime;
  }
}
