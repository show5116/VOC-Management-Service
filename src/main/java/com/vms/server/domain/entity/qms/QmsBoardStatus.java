package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsBoardStatusId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsBoardStatusId.class)
@Table(name = "QMS_BOARD_STATUS")
public class QmsBoardStatus {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "DEVICE")
  public String device;
  @Id
  @Column(name = "PCB_ID")
  public String pcbId;
  @Column(name = "MAKER")
  public String maker;
  @Column(name = "OWNER")
  public String owner;
  @Column(name = "LOCATION")
  public String location;
  @Column(name = "STATUS")
  public String status;
  @Column(name = "USER_COMMENT")
  public String userComment;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "SUPPLIER_PCB")
  public String supplierPcb;
}
