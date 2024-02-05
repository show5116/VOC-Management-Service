package com.vms.server.domain.entity.adm;

import javax.persistence.*;

import com.vms.server.domain.entity.adm.id.AdmUserInfoId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmUserInfoId.class)
@Table(name = "ADM_USER_INFO")
public class AdmUserInfo {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "USER_ID")
  public String userId;
  @Column(name = "USER_NAME")
  public String userName;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "PASSWORD")
  public String password;
  @Column(name = "PASSWORD_MD5")
  public String passwordMd5;
  @Column(name = "LANGUAGE_TYPE")
  public String languageType;
  @Column(name = "DEPARTMENT")
  public String department;
  @Column(name = "EXTERNAL_FLAG")
  public String externalFlag;
  @Column(name = "EXTERNAL_TYPE")
  public String externalType;
  @Column(name = "EMAIL_ID")
  public String emailId;
  @Column(name = "PHONE_NO")
  public String phoneNo;
  @Column(name = "ROLE_ID")
  public String roleId;
  @Column(name = "ACCESS_GROUP_ID")
  public String accessGroupId;
  @Column(name = "ADMISSION")
  public String admission;
  @Column(name = "EXPIRE_DATE")
  public String expireDate;
  @Column(name = "SIGN_IMAGE")
  public String signImage;
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
  @Column(name = "SUPER_USER")
  public String superUser;
  @Column(name = "ABN_LEVEL")
  public String abnLevel;
  @Column(name = "ABN_ABSENCE")
  public String abnAbsence;

  public void updateExpandFlag1(String expandFlag1){
    this.expandFlag1 =expandFlag1;
  }

}
