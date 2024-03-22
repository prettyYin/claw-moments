package com.moments.claw.domain.common.exception;

import com.moments.claw.domain.common.enums.CustomExceptionType;
import com.moments.claw.domain.common.enums.ResultEnum;

public class CustomException extends RuntimeException {
    /**异常错误编码*/
    private int code ;
    /**异常信息*/
    private String message;

    private CustomException(){}

    public CustomException(CustomExceptionType exceptionTypeEnum) {
        this.code = exceptionTypeEnum.getCode();
        this.message = exceptionTypeEnum.getMsg();
    }

    public CustomException(CustomExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public CustomException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
    }

    public CustomException(ResultEnum resultEnum, String message) {
        this.code = resultEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
