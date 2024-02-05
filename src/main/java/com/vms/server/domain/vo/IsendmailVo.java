package com.vms.server.domain.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IsendmailVo {
    private String systemName;
    private String fromEmail;
    private String toEmail;
    private String subject;
    private String sendFlag;
    private String createTime;
    private String createUser;
    private String sendTime;
    private String message;
    private String toCc;
    private String createUserR;
    private String deptId;

    public IsendmailVo(String createUserR, String createUser, String deptId, String systemName, String subject, String fromEmail, String toEmail, String toCc, String sendFlag, String createTime, String sendTime, String message) {
        this.createUserR = createUserR;
        this.createUser = createUser;
        this.deptId = deptId;
        this.systemName = systemName;
        this.subject = subject;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.toCc = toCc;
        this.sendFlag = sendFlag;
        this.createTime = createTime;
        this.sendTime = sendTime;
        this.message = message;
    }

}
