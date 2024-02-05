package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysEventLogId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysEventLogId.class)
@Table(name = "SYS_EVENT_LOG")
public class SysEventLog {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "USER_ID")
  public String userId;
  @Id
  @Column(name = "USE_PROGRAM")
  public String useProgram;
  @Id
  @Column(name = "MENU_DESCRIPTION")
  public String menuDescription;
  @Id
  @Column(name = "EVENT_DESCRIPTION")
  public String eventDescription;
  @Id
  @Column(name = "EVENT_TIME")
  public String eventTime;
  @Column(name = "IP_ADDR")
  public String ipAddr;
}
