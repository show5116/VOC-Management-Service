package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmRoleGroupItemId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmRoleGroupItemId.class)
@Table(name = "ADM_ROLE_GROUP_ITEM")
public class AdmRoleGroupItem {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "ROLE_GROUP")
  public String roleGroup;
  @Id
  @Column(name = "ROLE_OBJECT")
  public String roleObject;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "OBJECT_SEQ")
  public String objectSeq;
  @Column(name = "VISIBLE_FLAG")
  public String visibleFlag;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;
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
