package com.vms.server.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsSelectedListDto {
    private String plant;
    private String systemName;
    private String qmsNumber;
    private String revisionNo;
    private String itemType;
    private String itemCode;
    private String itemValue;

}
