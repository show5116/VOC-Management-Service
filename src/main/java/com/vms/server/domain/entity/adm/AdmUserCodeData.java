package com.vms.server.domain.entity.adm;

import com.vms.server.domain.entity.adm.id.AdmUserCodeDataId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@IdClass(AdmUserCodeDataId.class)
@Table(name = "ADM_USER_CODE_DATA")
public class AdmUserCodeData {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "TABLE_NAME")
  public String tableName;
  @Id
  @Column(name = "CODE_NAME")
  public String codeName;
  @Id
  @Column(name = "CODE_SEQ")
  public String codeSeq;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "CODE_GROUP1")
  public String codeGroup1;
  @Column(name = "CODE_GROUP2")
  public String codeGroup2;
  @Column(name = "CODE_GROUP3")
  public String codeGroup3;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;

  public AdmUserCodeData(String plant, String tableName, String codeName, String codeSeq, String description, String codeGroup1, String codeGroup2, String codeGroup3, String regTime, String regUser) {
    this.plant = plant;
    this.tableName = tableName;
    this.codeName = codeName;
    this.codeSeq = codeSeq;
    this.description = description;
    this.codeGroup1 = codeGroup1;
    this.codeGroup2 = codeGroup2;
    this.codeGroup3 = codeGroup3;
    this.regTime = regTime;
    this.regUser = regUser;
  }
}
