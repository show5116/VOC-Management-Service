package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentPmPlanId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentPmPlanId.class)
@Table(name = "AEM_EQUIPMENT_PM_PLAN")
public class AemEquipmentPmPlan {
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
  @Column(name = "PM_ITEM")
  public String pmItem;
  @Column(name = "PLAN_YEAR")
  public String planYear;
  @Id
  @Column(name = "PLAN_DATE")
  public String planDate;
  @Column(name = "PM_FLAG")
  public String pmFlag;
  @Column(name = "ACTUAL_PM_DATE")
  public String actualPmDate;
  @Column(name = "NEXT_PM_DATE")
  public String nextPmDate;
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "USER_COMMENT")
  public String userComment;
}
