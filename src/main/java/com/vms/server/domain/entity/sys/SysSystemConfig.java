package com.vms.server.domain.entity.sys;

import javax.persistence.*;

import com.vms.server.domain.entity.sys.id.SysSystemConfigId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(SysSystemConfigId.class)
@Table(name = "SYS_SYSTEM_CONFIG")
public class SysSystemConfig {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "SYSPARM_ID")
  public String sysparmId;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "SYSPARM_VALUE")
  public String sysparmValue;
  @Column(name = "INITIAL_VALUE")
  public String initialValue;
  @Column(name = "MAXIMUM_VALUE")
  public String maximumValue;
  @Column(name = "LAST_VALUE")
  public String lastValue;
  @Column(name = "VALUE_FORMAT")
  public String valueFormat;
}
