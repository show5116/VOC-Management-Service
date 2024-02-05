package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysRoleLogId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysRoleLogId.class)
@Table(name = "SYS_ROLE_LOG")
public class SysRoleLog {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Column(name = "USER_ID")
  public String userId;
  @Id
  @Column(name = "TRANS_TIME")
  public String transTime;
  @Id
  @Column(name = "ROLE_ITEM")
  public String roleItem;
  @Column(name = "ROLE_TARGET")
  public String roleTarget;
  @Column(name = "ROLE_VALUE_OLD")
  public String roleValueOld;
  @Column(name = "ROLE_VALUE_NEW")
  public String roleValueNew;
}
