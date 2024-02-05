package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysMenuStructureId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysMenuStructureId.class)
@Table(name = "SYS_MENU_STRUCTURE")
public class SysMenuStructure {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "GROUP_ID")
  public String groupId;
  @Id
  @Column(name = "MODULE_ID")
  public String moduleId;
  @Id
  @Column(name = "MENU_ID")
  public String menuId;
  @Column(name = "MENU_NAME_KOR")
  public String menuNameKor;
  @Column(name = "MENU_NAME_ENG")
  public String menuNameEng;
  @Column(name = "MENU_NAME_01")
  public String menuName01;
  @Column(name = "MENU_NAME_02")
  public String menuName02;
  @Column(name = "MENU_NAME_03")
  public String menuName03;
  @Column(name = "HAS_CHILD")
  public String hasChild;
  @Column(name = "PARENT_KEY")
  public String parentKey;
  @Column(name = "DISPLAY_DEPTH")
  public Integer displayDepth;
  @Column(name = "PATH")
  public String path;
  @Column(name = "ACTION_SEQ")
  public String actionSeq;
  @Column(name = "SHORTCUT_KEY")
  public String shortcutKey;
  @Column(name = "SEPERATE_BAR")
  public String seperateBar;
  @Column(name = "VISIBLE_FLAG")
  public String visibleFlag;
  @Column(name = "ENABLE_FLAG")
  public String enableFlag;
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
