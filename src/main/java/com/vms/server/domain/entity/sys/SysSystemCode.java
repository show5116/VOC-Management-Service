package com.vms.server.domain.entity.sys;

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
@IdClass(SysSystemCodeId.class)
@Table(name = "SYS_SYSTEM_CODE")
public class SysSystemCode {
  @Id
  @Column(name = "PLANT")
  public String plant;
  @Id
  @Column(name = "TABLE_NAME")
  public String tableName;
  @Column(name = "DESCRIPTION")
  public String description;
  @Column(name = "FORMAT_TYPE")
  public String formatType;
  @Column(name = "DECIMAL_LENGTH")
  public String decimalLength;
  @Column(name = "SIZE_LIMIT")
  public String sizeLimit;

  public SysSystemCode(String plant, String tableName, String description, String formatType, String decimalLength, String sizeLimit) {
    this.plant = plant;
    this.tableName = tableName;
    this.description = description;
    this.formatType = formatType;
    this.decimalLength = decimalLength;
    this.sizeLimit = sizeLimit;
  }
}
