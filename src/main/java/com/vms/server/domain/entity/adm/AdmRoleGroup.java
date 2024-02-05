package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmRoleGroupId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmRoleGroupId.class)
@Table(name = "ADM_ROLE_GROUP")
public class AdmRoleGroup {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "ROLE_GROUP")
  public String roleGroup;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "ROLE_TYPE")
  public String roleType;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;

  public AdmRoleGroup(String plant, String roleGroup, String description, String roleType, String regTime, String regUser) {
    this.plant = plant;
    this.roleGroup = roleGroup;
    this.description = description;
    this.roleType = roleType;
    this.regTime = regTime;
    this.regUser = regUser;
  }
}
