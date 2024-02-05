package com.vms.server.domain.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginDto {

    public String userId;
    public String password;
    public String plant;

    public LoginDto(String userId, String password, String plant) {
        this.userId = userId;
        this.password = password;
        this.plant = plant;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPlant() {
        return plant;
    }
}
