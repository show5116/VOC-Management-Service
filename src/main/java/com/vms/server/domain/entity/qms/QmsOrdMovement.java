package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsOrdMovementId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsOrdMovementId.class)
@Table(name = "QMS_ORD_MOVEMENT")
public class QmsOrdMovement {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Id
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Id
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Id
  @Column(name = "SEQ")
  public String seq;
  @Column(name = "REQ_CONTENT")
  public String reqContent;
  @Column(name = "MAT_CATEGORY")
  public String matCategory;
  @Column(name = "MAT_CODE")
  public String matCode;
  @Column(name = "BIB_NO")
  public String bibNo;
  @Column(name = "FROM_SUBC")
  public String fromSubc;
  @Column(name = "TO_SUBC")
  public String toSubc;
  @Column(name = "QTY")
  public String qty;
}
