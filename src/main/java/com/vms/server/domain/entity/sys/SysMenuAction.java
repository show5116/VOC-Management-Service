package com.vms.server.domain.entity.sys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_MENU_ACTION")
public class SysMenuAction {
  @Id
  @Column(name = "ACTION_SEQ")
  public String actionSeq;
  @Column(name = "MODULE_ID")
  public String moduleId;
  @Column(name = "ACTION_TYPE")
  public String actionType;
  @Column(name = "ACTION_NAME_KOR")
  public String actionNameKor;
  @Column(name = "ACTION_NAME_ENG")
  public String actionNameEng;
  @Column(name = "ACTION_NAME_01")
  public String actionName01;
  @Column(name = "ACTION_NAME_02")
  public String actionName02;
  @Column(name = "ACTION_NAME_03")
  public String actionName03;
  @Column(name = "ACTION")
  public String action;
  @Column(name = "CONTROL_TYPE")
  public String controlType;
  @Column(name = "CONTROL_VALUE")
  public String controlValue;
  @Column(name = "ENABLE_TOOLBAR")
  public String enableToolbar;
  @Column(name = "TOOLBAR_ICON")
  public String toolbarIcon;
  @Column(name = "TOOLBAR_TEXT_KOR")
  public String toolbarTextKor;
  @Column(name = "TOOLBAR_TEXT_ENG")
  public String toolbarTextEng;
  @Column(name = "TOOLBAR_TEXT_01")
  public String toolbarText01;
  @Column(name = "TOOLBAR_TEXT_02")
  public String toolbarText02;
  @Column(name = "TOOLBAR_TEXT_03")
  public String toolbarText03;
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
