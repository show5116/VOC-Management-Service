package com.vms.server.domain.entity.sys;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SYS_SYSTEM_CONSTANT")
public class SysSystemConstant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PLANT")
  public String plant;
  @Column(name = "CONSTANT_NAME")
  public String constantName;
  @Column(name = "CONSTANT_TYPE")
  public String constantType;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "CONSTANT_VALUE")
  public String constantValue;
}
