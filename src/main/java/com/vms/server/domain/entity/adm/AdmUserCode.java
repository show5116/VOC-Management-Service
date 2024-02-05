package com.vms.server.domain.entity.adm;

import com.vms.server.domain.entity.adm.id.AdmUserCodeId;
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
@IdClass(AdmUserCodeId.class)
@Table(name = "ADM_USER_CODE")
public class AdmUserCode {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "TABLE_NAME")
  public String tableName;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "TABLE_TYPE")
  public String tableType;
  @Column(name = "FORMAT_TYPE")
  public String formatType;
  @Column(name = "DECIMAL_LENGTH")
  public String decimalLength;
  @Column(name = "SIZE_LIMIT")
  public String sizeLimit;
  @Column(name = "REG_TIME")
  public String regTime;
  @Column(name = "REG_USER")
  public String regUser;

  public AdmUserCode(String plant, String tableName, String description, String tableType, String formatType, String decimalLength, String sizeLimit, String regTime, String regUser) {
    this.plant = plant;
    this.tableName = tableName;
    this.description = description;
    this.tableType = tableType;
    this.formatType = formatType;
    this.decimalLength = decimalLength;
    this.sizeLimit = sizeLimit;
    this.regTime = regTime;
    this.regUser = regUser;
  }
}
