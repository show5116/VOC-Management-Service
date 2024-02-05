package com.vms.server.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SysFileLogDto {
    private String plant;
    private List<String> userId;
    private String transTime;
    private String ipAddr;
    private String fileName;
    private String status;

    private String startDate;
    private String endDate;
    private String userName;
    private String deptName;
    private String transTimeR;
}
