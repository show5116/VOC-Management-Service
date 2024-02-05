package com.vms.server.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QmsPgmnameDataDto {
    private String plant;
    private String programId;
    private String device;
    private String process;
    private String temperature;
    private String multi;
    private String pass;
    private String tester;
    private String proberType;
    private String proberValue;
    private String internalRevision;
    private String externalRevision;
    private String regDate;
    private String regUser;
    private List<String> devices;


}
