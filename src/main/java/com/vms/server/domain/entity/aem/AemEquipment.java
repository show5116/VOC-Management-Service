package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentId.class)
@Table(name = "AEM_EQUIPMENT")
public class AemEquipment {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Column(name = "WORK_CENTER")
  public String workCenter;
  @Column(name = "SYS_TRANS_TIME")
  public String sysTransTime;
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "EVENT_ID")
  public String eventId;
  @Column(name = "AVAILABILITY")
  public String availability;
  @Column(name = "DOWN_REASON")
  public String downReason;
  @Column(name = "MAIN_STATUS")
  public String mainStatus;
  @Column(name = "STATUS_CATEGORY")
  public String statusCategory;
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "USER_COMMENT")
  public String userComment;
  @Column(name = "EQUIPMENT_UNIT")
  public String equipmentUnit;
}
