package com.vms.server.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysFileLogVo {
    private String userId;
    private String userName;
    private String deptName;
    private String status;
    private String ipAddr;
    private String fileName;
    private String transTime;

    public SysFileLogVo(String userId, String userName, String deptName, String status, String ipAddr, String fileName, String transTime) {
        this.userId = userId;
        this.userName = userName;
        this.deptName = deptName;
        this.status = status;
        this.ipAddr = ipAddr;
        this.fileName = fileName;
        this.transTime = transTime;
    }
}
