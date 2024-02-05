package com.vms.server.domain.entity.qms;

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
@Table(name = "QMS_MAT_STATUS_BACKUP")
public class QmsMatStatusBackup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "MAT_LOT_NUMBER")
  public String matLotNumber;
  @Column(name = "SYS_TRANS_TIME")
  public String sysTransTime;
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "LAST_HST_SEQ")
  public String lastHstSeq;
  @Column(name = "TRANSACTION")
  public String transaction;
  @Column(name = "MAT_CATEGORY")
  public String matCategory;
  @Column(name = "MAT_CODE")
  public String matCode;
  @Column(name = "BIB_NO")
  public String bibNo;
  @Column(name = "SUBC_CODE")
  public String subcCode;
  @Column(name = "QTY")
  public String qty;
  @Column(name = "UNIT_PRICE")
  public String unitPrice;
  @Column(name = "CREATION_TIME")
  public String creationTime;
  @Column(name = "CREATION_SUBC")
  public String creationSubc;
  @Column(name = "STATUS")
  public String status;
  @Column(name = "SYSTEM_NAME")
  public String systemName;
  @Column(name = "QMS_NUMBER")
  public String qmsNumber;
  @Column(name = "REVISION_NO")
  public String revisionNo;
  @Column(name = "SEQ")
  public String seq;
}
