package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentHistoryId.class)
@Table(name = "AEM_EQUIPMENT_HISTORY")
public class AemEquipmentHistory {
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
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Column(name = "PREV_TRANS_TIME")
  public String prevTransTime;
  @Column(name = "EVENT_ID_OLD")
  public String eventIdOld;
  @Column(name = "EVENT_ID_NEW")
  public String eventIdNew;
  @Column(name = "AVAILABILITY_OLD")
  public String availabilityOld;
  @Column(name = "AVAILABILITY_NEW")
  public String availabilityNew;
  @Column(name = "DOWN_REASON")
  public String downReason;
  @Column(name = "MAIN_STATUS_OLD")
  public String mainStatusOld;
  @Column(name = "MAIN_STATUS_NEW")
  public String mainStatusNew;
  @Column(name = "STATUS_CATEGORY")
  public String statusCategory;
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "USER_COMMENT")
  public String userComment;
  @Column(name = "EQUIPMENT_UNIT")
  public String equipmentUnit;
}
