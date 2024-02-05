package com.vms.server.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsLsDetailDto {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String seq;
    private String checkList;
    private String remark;
    private String keyWord;
    private String functionType;
    private List<String> processes;
}
