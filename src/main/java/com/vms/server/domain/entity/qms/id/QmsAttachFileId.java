package com.vms.server.domain.entity.qms.id;

import java.io.Serializable;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@Embeddable
public class QmsAttachFileId implements Serializable {
  @Column(name = "plant")
  private String plant;
  @Column(name = "system_name")
  private String systemName;
  @Column(name = "system_name_mtype")
  private String systemNameMtype;
  @Column(name = "system_name_stype")
  private String systemNameStype;
  @Column(name = "qms_number")
  private String qmsNumber;
  @Column(name = "revision_no")
  private String revisionNo;
  @Column(name = "file_seq")
  private String fileSeq;

  public QmsAttachFileId(String plant, String systemName, String systemNameMtype, String systemNameStype, String qmsNumber, String revisionNo, String fileSeq) {
    this.plant = plant;
    this.systemName = systemName;
    this.systemNameMtype = systemNameMtype;
    this.systemNameStype = systemNameStype;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.fileSeq = fileSeq;
  }
}
