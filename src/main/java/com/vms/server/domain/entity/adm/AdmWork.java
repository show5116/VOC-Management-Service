package com.vms.server.domain.entity.adm;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ADM_WORK")
public class AdmWork {

  @Id
  @Column(name = "OFC_ID")
  public String ofcId;
  @Column(name = "OFC_NAME")
  public String ofcName;
  @Column(name = "OFC_COMMENT")
  public String ofcComment;
  @Column(name = "OFC_ORDER")
  public Integer ofcOrder;

  public AdmWork(String ofcId, String ofcName, String ofcComment, int ofcOrder) {
    this.ofcId = ofcId;
    this.ofcName = ofcName;
    this.ofcComment = ofcComment;
    this.ofcOrder = ofcOrder;
  }

  public AdmWork(String ofcId, String ofcName, int ofcOrder) {
    this.ofcId = ofcId;
    this.ofcName = ofcName;
    this.ofcOrder = ofcOrder;
  }
}
