package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsEcnSelectedListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsEcnSelectedListId.class)
@Table(name = "QMS_ECN_SELECTED_LIST")
public class QmsEcnSelectedList {
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
  @Column(name = "ITEM_VALUE")
  public String itemValue;
  @Id
  @Column(name = "ITEM_CODE")
  public String itemCode;
}
