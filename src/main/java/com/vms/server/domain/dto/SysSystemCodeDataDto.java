package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysSystemCodeDataDto {

  private String plant;
  private String tableName;
  private String codeName;
  private String codeSeq;
  private String description;
  private String codeGroup1;
  private String codeGroup2;
  private String codeGroup3;
  private String specialData1;
  private String specialData2;
  private String specialData3;
  private String orderBy;
  private Boolean ascYn;
  private String code;
  private String value;
  private boolean codeView;
  private List<String> siteList;

  public SysSystemCodeDataDto(String codeName, String description) {
    this.codeName = codeName;
    this.description = description;
  }

  public SysSystemCodeDataDto(String plant, String tableName, String codeName, String codeSeq, String description, String codeGroup1, String codeGroup2, String codeGroup3, String specialData1, String specialData2, String specialData3) {
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

  public Boolean isAscYn() {
    return ascYn;
  }
}
