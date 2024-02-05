package com.vms.server.domain.entity.aem;

import javax.persistence.*;

import com.vms.server.domain.entity.aem.id.AemEquipmentPmItemId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AemEquipmentPmItemId.class)
@Table(name = "AEM_EQUIPMENT_PM_ITEM")
public class AemEquipmentPmItem {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Id
  @Column(name = "PM_ITEM")
  public String pmItem;
  @Id
  @Column(name = "PM_ITEM_SEQ")
  public String pmItemSeq;
  @Column(name = "PM_CONTENT")
  public String pmContent;
  @Column(name = "PM_METHOD")
  public String pmMethod;
  @Column(name = "PM_SPEC")
  public String pmSpec;
  @Column(name = "PM_PERIOD")
  public String pmPeriod;
  @Column(name = "REMARK")
  public String remark;
  @Column(name = "USER_ID")
  public String userId;
}
