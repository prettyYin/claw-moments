package com.moments.claw.domain.common.enums;

public enum CustomExceptionType {
    USER_INPUT_ERROR(400,"用户输入异常"),
    UN_AUTHORIZED(401,"用户未授权"),
    REDIRECT_ERROR(403,"重定向异常"),
    NOT_FOUND(404,"页面丢失了"),
    SYSTEM_ERROR (500,"系统服务异常"),
    OTHER_ERROR(999,"其他未知异常");

    CustomExceptionType(int code, String typeDesc) {
        this.code = code;
        this.msg = typeDesc;
    }

    private String msg;//异常类型中文描述

    private int code; //code

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
