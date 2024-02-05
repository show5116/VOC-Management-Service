package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysMenuFavoriteId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysMenuFavoriteId.class)
@Table(name = "SYS_MENU_FAVORITE")
public class SysMenuFavorite {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "GROUP_ID")
  public String groupId;
  @Id
  @Column(name = "USER_ID")
  public String userId;
  @Id
  @Column(name = "MODULE_ID")
  public String moduleId;
  @Id
  @Column(name = "TOOLBAR_SEQ")
  public String toolbarSeq;
  @Id
  @Column(name = "ACTION_SEQ")
  public String actionSeq;
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
