package com.vms.server.domain.entity.sys;

import com.vms.server.domain.entity.sys.id.SysSystemCodeDataId;
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
@IdClass(SysSystemCodeDataId.class)
@Table(name = "SYS_SYSTEM_CODE_DATA")
public class SysSystemCodeData {
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
  @Column(name = "SPECIAL_DATA1")
  public String specialData1;
  @Column(name = "SPECIAL_DATA2")
  public String specialData2;
  @Column(name = "SPECIAL_DATA3")
  public String specialData3;

  public SysSystemCodeData(String plant, String tableName, String codeName, String codeSeq, String description, String codeGroup1, String codeGroup2, String codeGroup3, String specialData1, String specialData2, String specialData3) {
    this.plant = plant;
    this.tableName = tableName;
    this.codeName = codeName;
    this.codeSeq = codeSeq;
    this.description = description;
    this.codeGroup1 = codeGroup1;
    this.codeGroup2 = codeGroup2;
    this.codeGroup3 = codeGroup3;
    this.specialData1 = specialData1;
    this.specialData2 = specialData2;
    this.specialData3 = specialData3;
  }
}
