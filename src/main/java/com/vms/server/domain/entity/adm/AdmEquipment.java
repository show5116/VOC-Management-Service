package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmEquipmentId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmEquipmentId.class)
@Table(name = "ADM_EQUIPMENT")
public class AdmEquipment {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "EQUIPMENT_ID")
  public String equipmentId;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "SEQ_NO")
  public String seqNo;
  @Column(name = "TYPE")
  public String type;
  @Column(name = "MODEL")
  public String model;
  @Column(name = "MAKER")
  public String maker;
  @Column(name = "LOCATION")
  public String location;
  @Column(name = "OWNER")
  public String owner;
  @Column(name = "STATUS_CATEGORY")
  public String statusCategory;
  @Column(name = "INSTALL_TIME")
  public String installTime;
  @Column(name = "DEL_FLAG")
  public String delFlag;
  @Column(name = "DELETE_TIME")
  public String deleteTime;
  @Column(name = "INCLUDE_CAPA")
  public String includeCapa;
  @Column(name = "INCLUDE_MON")
  public String includeMon;
  @Column(name = "INCLUDE_ARATE")
  public String includeArate;
  @Column(name = "SINGLE_LOT")
  public String singleLot;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;
  @Column(name = "EXPAND_FLAG1")
  public String expandFlag1;
  @Column(name = "EXPAND_FLAG2")
  public String expandFlag2;
  @Column(name = "EXPAND_FLAG3")
  public String expandFlag3;
  @Column(name = "EXPAND_FLAG4")
  public String expandFlag4;
  @Column(name = "EXPAND_FLAG5")
  public String expandFlag5;
  @Column(name = "EXPAND_FIELD1")
  public String expandField1;
  @Column(name = "EXPAND_FIELD2")
  public String expandField2;
  @Column(name = "EXPAND_FIELD3")
  public String expandField3;
  @Column(name = "EXPAND_FIELD4")
  public String expandField4;
  @Column(name = "EXPAND_FIELD5")
  public String expandField5;
  @Column(name = "EQUIPMENT_TYPE")
  public String equipmentType;
  @Id
  @Column(name = "SITE")
  public String site;
  @Id
  @Column(name = "SERIAL_NO")
  public String serialNo;
  @Column(name = "REMARK")
  public String remark;
}
