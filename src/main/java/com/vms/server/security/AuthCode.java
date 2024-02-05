package com.vms.server.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthCode {
    WRONG_PASSWORD("000","wrongPassword"),
    WRONG_TYPE_TOKEN("001","wrongTypeToken"),
    EXPIRED_TOKEN("002","expiredToken"),
    UNSUPPORTED_TOKEN("003","unsupportedToken"),
    WRONG_TOKEN("004","wrongToken"),
    ACCESS_DENIED("005","accessDenied"),
    UNAUTHORIZED("006","unAuthorized"),
    UNKNOWN_ERROR("010","unknownError"),
    LOG_IN("020","logInSuccess");

    private String code;

    private String message;

}