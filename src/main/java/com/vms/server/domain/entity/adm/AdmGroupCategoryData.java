package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmGroupCategoryDataId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmGroupCategoryDataId.class)
@Table(name = "ADM_GROUP_CATEGORY_DATA")
public class AdmGroupCategoryData {
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
  @Id
  @Column(name = "GROUP_OBJECT")
  public String groupObject;
  @Id
  @Column(name = "GROUP_VALUE")
  public String groupValue;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;
}
