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
@Table(name = "ADM_POSITION")
public class AdmPosition {

  @Id
  @Column(name = "POS_ID")
  public String posId;
  @Column(name = "POS_NAME")
  public String posName;
  @Column(name = "POS_COMMENT")
  public String posComment;
  @Column(name = "POS_ORDER")
  public Integer posOrder;

  public AdmPosition(String posId, String posName, String posComment, int posOrder) {
    this.posId = posId;
    this.posName = posName;
    this.posComment = posComment;
    this.posOrder = posOrder;
  }

  public AdmPosition(String posId, String posName, int posOrder) {
    this.posId = posId;
    this.posName = posName;
    this.posOrder = posOrder;
  }
}
