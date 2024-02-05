package com.vms.server.domain.entity.sys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysMenuGroupId.class)
@Table(name = "SYS_MENU_GROUP")
public class SysMenuGroup {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "MODULE_ID")
  public String moduleId;
  @Id
  @Column(name = "GROUP_ID")
  public String groupId;
  @Column(name = "GROUP_NAME")
  public String groupName;
  @Column(name = "VISIBLE_FLAG")
  public String visibleFlag;
  @Column(name = "DESCRIPTION")
  public String description;
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
}
