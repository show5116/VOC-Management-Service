package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentBmDtlHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentBmDtlHistoryId.class)
@Table(name = "AEM_EQUIPMENT_BM_DTL_HISTORY")
public class AemEquipmentBmDtlHistory {
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
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "ACTUAL_VALUE")
  public String actualValue;
  @Column(name = "ACTUAL_RESULT")
  public String actualResult;
  @Id
  @Column(name = "BM_START_DATE")
  public String bmStartDate;
  @Id
  @Column(name = "RESOURCE_ID")
  public String resourceId;
  @Id
  @Column(name = "SLOT_NO")
  public String slotNo;
  @Id
  @Column(name = "RES_SERIAL_NO")
  public String resSerialNo;
  @Column(name = "CHANGE_FLAG")
  public String changeFlag;
  @Column(name = "CHG_SERIAL_NO")
  public String chgSerialNo;
  @Column(name = "CHG_REPLACE_DATE")
  public String chgReplaceDate;
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "USER_COMMENT")
  public String userComment;
  @Column(name = "CAL_RESULT")
  public String calResult;
  @Column(name = "DIA_RESULT")
  public String diaResult;
  @Column(name = "DOA")
  public String doa;
}
