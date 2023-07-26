package com.hotel.exception;

public class SystemException extends Exception {

    private String errorCode;
    private String errorMsg;

    public SystemException() {
    }

    public SystemException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SystemException{");
        sb.append("errorCode='").append(errorCode).append('\'');
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
