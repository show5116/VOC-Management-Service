package com.vms.server.exception;

public class CustomException extends  RuntimeException {
    private ErrorCode errorCode;
    private String code;
    private String errorMessage;

    public CustomException(ErrorCode errorCode) {
        this.code = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }
    public CustomException(String code , String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }
    public String getCode(){
        return code;
    }
    public String getErrorMessage(){
        return errorMessage;
    }

}