package com.vms.server.domain.dto;

import com.vms.server.domain.entity.adm.AdmUserInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonCodeDto {
    private String code;
    private String value;
    private String description;

    public CommonCodeDto(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public CommonCodeDto(String code, String value, String description) {
        this.code = code;
        this.value = value;
        this.description = description;
    }

    public CommonCodeDto(AdmUserInfo admUserInfo) {
        this.code = admUserInfo.getPlant();
        this.value = admUserInfo.getPlant();
    }
}
