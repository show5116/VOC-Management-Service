package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsActionItemListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsActionItemListId.class)
@Table(name = "QMS_ACTION_ITEM_LIST")
public class QmsActionItemList {
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
  @Column(name = "SEQ_NO")
  public String seqNo;
  @Column(name = "ACTION_ITEM")
  public String actionItem;
  @Column(name = "ACT_REV_NO")
  public String actRevNo;
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "USER_NAME")
  public String userName;
  @Column(name = "COMPLETED_DATE")
  public String completedDate;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "COMPLETED_FLAG")
  public String completedFlag;

  public QmsActionItemList(String plant, String systemName, String qmsNumber, String seqNo, String actionItem, String actRevNo, String userId, String userName, String completedDate, String remark, String completedFlag) {
    this.plant = plant;
    this.systemName = systemName;
    this.qmsNumber = qmsNumber;
    this.seqNo = seqNo;
    this.actionItem = actionItem;
    this.actRevNo = actRevNo;
    this.userId = userId;
    this.userName = userName;
    this.completedDate = completedDate;
    this.remark = remark;
    this.completedFlag = completedFlag;
  }
}
