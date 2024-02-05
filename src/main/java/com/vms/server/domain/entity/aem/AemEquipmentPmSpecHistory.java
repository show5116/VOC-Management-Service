package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentPmSpecHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentPmSpecHistoryId.class)
@Table(name = "AEM_EQUIPMENT_PM_SPEC_HISTORY")
public class AemEquipmentPmSpecHistory {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Id
  @Column(name = "SPEC_ID")
  public String specId;
  @Id
  @Column(name = "PM_CYCLE")
  public String pmCycle;
  @Id
  @Column(name = "PLAN_DATE")
  public String planDate;
  @Column(name = "JUDGMENT")
  public String judgment;
  @Column(name = "INTERLOCK_FLAG")
  public String interlockFlag;
  @Column(name = "ACTUAL_PM_DATE")
  public String actualPmDate;
  @Column(name = "FINISHED_DATE")
  public String finishedDate;
  @Column(name = "NEXT_PM_DATE")
  public String nextPmDate;
  @Column(name = "PM_ACTION")
  public String pmAction;
  @Column(name = "ACTION_USER")
  public String actionUser;
  @Column(name = "ACTION_DATE")
  public String actionDate;
  @Column(name = "ACTION_COMMENT")
  public String actionComment;
  @Column(name = "UPDATE_USER")
  public String updateUser;
  @Column(name = "UPDATE_DATE")
  public String updateDate;
  @Column(name = "CREATE_USER")
  public String createUser;
  @Column(name = "CREATE_DATE")
  public String createDate;
  @Column(name = "USER_COMMENT")
  public String userComment;
}
