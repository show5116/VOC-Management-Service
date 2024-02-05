package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmEqpconfHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmEqpconfHistoryId.class)
@Table(name = "ADM_EQPCONF_HISTORY")
public class AdmEqpconfHistory {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Id
  @Column(name = "SITE")
  public String site;
  @Id
  @Column(name = "SERIAL_NO")
  public String serialNo;
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Id
  @Column(name = "RESOURCE_ID")
  public String resourceId;
  @Id
  @Column(name = "SLOT_NO")
  public String slotNo;
  @Id
  @Column(name = "RES_SERIAL_NO")
  public String resSerialNo;
  @Column(name = "REPLACE_DATE")
  public String replaceDate;
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "DML_FLAG")
  public String dmlFlag;
  @Id
  @Column(name = "SLOT_TYPE")
  public String slotType;
}
