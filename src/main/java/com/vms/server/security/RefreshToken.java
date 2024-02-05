package com.vms.server.security;

import lombok.Getter;

@Getter
public class RefreshToken {
    private String userId;
    private String plant;
    private String ip;

    public RefreshToken(String userId, String plant, String ip) {
        this.userId = userId;
        this.plant = plant;
        this.ip = ip;
    }

    public RefreshToken(String stringFiled){
        for( String row : stringFiled.split(",") ){
            String value = row.substring(row.indexOf(":")+1, row.length());
            switch (row.substring(0,row.indexOf(":"))){
                case "userId":
                    this.userId = value;
                    break;
                case "plant":
                    this.plant = value;
                    break;
                case "ip":
                    this.ip = value;
                    break;
            }
        }
    };

    public String getRefreshTokenToString() {
        return "userId:"+this.userId+","+"plant:"+this.plant+","+"ip:"+this.ip;
    }

}
