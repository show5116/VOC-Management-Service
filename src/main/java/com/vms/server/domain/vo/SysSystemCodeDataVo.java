package com.vms.server.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SysSystemCodeDataVo {

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
  private List<String> siteList;

  public Boolean isAscYn() {
    return ascYn;
  }
}
