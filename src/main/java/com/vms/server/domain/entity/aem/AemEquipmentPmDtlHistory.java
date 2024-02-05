package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentPmDtlHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentPmDtlHistoryId.class)
@Table(name = "AEM_EQUIPMENT_PM_DTL_HISTORY")
public class AemEquipmentPmDtlHistory {
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
  @Column(name = "ACTUAL_PM_DATE")
  public String actualPmDate;
  @Column(name = "SPEC_ID")
  public String specId;
  @Column(name = "PM_CYCLE")
  public String pmCycle;
  @Id
  @Column(name = "PM_ITEM")
  public String pmItem;
  @Id
  @Column(name = "PLAN_DATE")
  public String planDate;
  @Column(name = "ACTUAL_VALUE")
  public String actualValue;
  @Column(name = "UNIT")
  public String unit;
  @Column(name = "DIA_RESULT")
  public String diaResult;
  @Column(name = "CREATE_USER")
  public String createUser;
  @Column(name = "CREATE_DATE")
  public String createDate;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "USER_COMMENT")
  public String userComment;
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
  @Column(name = "CAL_RESULT")
  public String calResult;
  @Column(name = "ACTUAL_RESULT")
  public String actualResult;
  @Column(name = "DOA")
  public String doa;
}
