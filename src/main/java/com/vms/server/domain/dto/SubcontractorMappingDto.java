package com.vms.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubcontractorMappingDto {

     private String subctrtDesc;
     private String subctrtCode;
     private String plant;
     private String siteCode;
     private String siteValue;
     private String facility;
     private List<String> processes;
}
