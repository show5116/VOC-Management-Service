package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysClientHistoryDto {
    private String computerName;
    private String useProgram;
    private String programVersion;
    private String clientId;
    private String inPlant;
    private String loginTime;
    private String logoutTime;
    private String workingUser;
    private String status;
    private String languageSet;
    private String lastTransCode;
    private String lastTransTime;
    private String ipAddr;
    private int failCount;

    private String plant;
    private String login;
    private String startDate;
    private String endDate;
    private String useTime;
}
