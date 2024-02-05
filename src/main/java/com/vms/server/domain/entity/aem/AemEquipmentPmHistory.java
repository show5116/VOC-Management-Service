package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentPmHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentPmHistoryId.class)
@Table(name = "AEM_EQUIPMENT_PM_HISTORY")
public class AemEquipmentPmHistory {
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
  @Column(name = "PM_END_DATE")
  public String pmEndDate;
  @Id
  @Column(name = "PM_ITEM")
  public String pmItem;
  @Id
  @Column(name = "PM_PLAN_DATE")
  public String pmPlanDate;
  @Column(name = "UNIT")
  public String unit;
  @Column(name = "ASSY")
  public String assy;
  @Column(name = "SITUATION")
  public String situation;
  @Column(name = "ACTION")
  public String action;
  @Column(name = "STATE_USER")
  public String stateUser;
  @Column(name = "PM_STATE")
  public String pmState;
  @Column(name = "ACTION_USER")
  public String actionUser;
  @Column(name = "PM_ACTION")
  public String pmAction;
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "USER_COMMENT")
  public String userComment;
}
