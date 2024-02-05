package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmGroupCategoryId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmGroupCategoryId.class)
@Table(name = "ADM_GROUP_CATEGORY")
public class AdmGroupCategory {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "GROUP_TARGET")
  public String groupTarget;
  @Id
  @Column(name = "GROUP_INDEX")
  public String groupIndex;
  @Id
  @Column(name = "GROUP_CATEGORY")
  public String groupCategory;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "MUST_FLAG")
  public String mustFlag;
  @Column(name = "USER_TABLE")
  public String userTable;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;
}
