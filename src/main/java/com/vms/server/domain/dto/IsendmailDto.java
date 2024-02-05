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
public class IsendmailDto {
    private String seq;
    private List<String> systemName;
    private String fromEmail;
    private String toEmail;
    private String subject;
    private List<String> sendFlag;
    private String createTime;
    private List<String> createUser;
    private String sendTime;
    private String errorMsg;
    private String fileId;
    private String message;
    private String toCc;
    private String toBcc;
    private String isHtml;
    private String expandField1;
    private String expandField2;
    private String expandField3;
    private String expandField4;
    private String expandField5;

    private String plant;
    private String startDate;
    private String endDate;
    private String createUserR;
    private String deptId;
    private String description;
    private String code;


    public IsendmailDto(String description, String code) {
        this.description = description;
        this.code = code;
    }
}
