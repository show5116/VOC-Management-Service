package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsSelectedListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsSelectedListId.class)
@Table(name = "QMS_SELECTED_LIST")
public class QmsSelectedList {
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
  @Column(name = "ITEM_TYPE")
  public String itemType;
  @Id
  @Column(name = "ITEM_CODE")
  public String itemCode;
  @Column(name = "ITEM_VALUE")
  public String itemValue;

  public QmsSelectedList(String plant, String systemName, String qmsNumber, String revisionNo, String itemType, String itemCode, String itemValue) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.revisionNo = revisionNo;
    this.itemType = itemType;
    this.itemCode = itemCode;
    this.itemValue = itemValue;
  }
}
