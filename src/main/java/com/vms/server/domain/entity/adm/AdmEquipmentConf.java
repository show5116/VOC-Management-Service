package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmEquipmentConfId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmEquipmentConfId.class)
@Table(name = "ADM_EQUIPMENT_CONF")
public class AdmEquipmentConf {
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
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "USER_ID")
  public String userId;
  @Id
  @Column(name = "SLOT_TYPE")
  public String slotType;
}
