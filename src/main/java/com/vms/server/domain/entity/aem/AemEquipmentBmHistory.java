package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentBmHistoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentBmHistoryId.class)
@Table(name = "AEM_EQUIPMENT_BM_HISTORY")
public class AemEquipmentBmHistory {
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
  @Column(name = "PM_FLAG")
  public String pmFlag;
  @Id
  @Column(name = "BM_START_DATE")
  public String bmStartDate;
  @Column(name = "BM_END_DATE")
  public String bmEndDate;
  @Column(name = "BM_STATUS")
  public String bmStatus;
  @Column(name = "ISSUE_USER")
  public String issueUser;
  @Column(name = "ISSUE_REMARK")
  public String issueRemark;
  @Column(name = "REASON_CODE")
  public String reasonCode;
  @Column(name = "STATE_CODE")
  public String stateCode;
  @Column(name = "ACTION_CODE")
  public String actionCode;
  @Column(name = "REASON_REMARK")
  public String reasonRemark;
  @Column(name = "STATE_REMARK")
  public String stateRemark;
  @Column(name = "ACTION_REMARK")
  public String actionRemark;
  @Column(name = "REPAIR_START_DATE")
  public String repairStartDate;
  @Column(name = "REPAIR_END_DATE")
  public String repairEndDate;
  @Column(name = "REPAIR_USER")
  public String repairUser;
  @Column(name = "REPAIR_REMARK")
  public String repairRemark;
  @Column(name = "USER_ID")
  public String userId;
}
