package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmAuthorityRoleId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmAuthorityRoleId.class)
@Table(name = "ADM_AUTHORITY_ROLE")
public class AdmAuthorityRole {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "ROLE_ID")
  public String roleId;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "ROLE_GRADE")
  public String roleGrade;
  @Column(name = "PLANT_GROUP")
  public String plantGroup;
  @Column(name = "OPERATION_GROUP")
  public String operationGroup;
  @Column(name = "MES_MENU_GROUP")
  public String mesMenuGroup;
  @Column(name = "REPORT_MENU_GROUP")
  public String reportMenuGroup;
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

  public AdmAuthorityRole(String plant, String roleId, String description, String roleGrade, String plantGroup, String operationGroup, String mesMenuGroup, String reportMenuGroup, String regTime, String regUser,  String expandField1, String expandField2, String expandField3, String expandField4, String expandField5) {
    this.plant = plant;
    this.roleId = roleId;
    this.description = description;
    this.roleGrade = roleGrade;
    this.plantGroup = plantGroup;
    this.operationGroup = operationGroup;
    this.mesMenuGroup = mesMenuGroup;
    this.reportMenuGroup = reportMenuGroup;
    this.regTime = regTime;
    this.regUser = regUser;
    this.expandField1 = expandField1;
    this.expandField2 = expandField2;
    this.expandField3 = expandField3;
    this.expandField4 = expandField4;
    this.expandField5 = expandField5;
  }
}
