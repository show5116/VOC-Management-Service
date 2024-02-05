package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsRmaLotListId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsRmaLotListId.class)
@Table(name = "QMS_RMA_LOT_LIST")
public class QmsRmaLotList {
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
  @Column(name = "TIMEKEY")
  public java.sql.Timestamp timekey;
  @Column(name = "LOT_NUMBER")
  public String lotNumber;
  @Column(name = "MARKING_INFO")
  public String markingInfo;
  @Column(name = "QTY")
  public String qty;
  @Column(name = "CUSTOMER_REMARK")
  public String customerRemark;
  @Column(name = "CUSTOMER_SERIAL_NO")
  public String customerSerialNo;
}
