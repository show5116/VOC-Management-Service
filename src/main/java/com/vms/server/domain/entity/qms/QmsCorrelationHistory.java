package com.vms.server.domain.entity.qms;

import javax.persistence.*;

import com.vms.server.domain.entity.qms.id.QmsCorrelationHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(QmsCorrelationHistoryId.class)
@Table(name = "QMS_CORRELATION_HISTORY")
public class QmsCorrelationHistory {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SITE")
  public String site;
  @Id
  @Column(name = "DEVICE")
  public String device;
  @Id
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Id
  @Column(name = "PCB_ID")
  public String pcbId;
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "MANAGER")
  public String manager;
  @Column(name = "APPLY_FLAG")
  public String applyFlag;
  @Column(name = "APPLY_DATE")
  public String applyDate;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "REG_DATE")
  public String regDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
}
