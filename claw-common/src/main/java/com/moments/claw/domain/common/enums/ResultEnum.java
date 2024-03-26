package com.moments.claw.domain.common.enums;

import lombok.AllArgsConstructor;

/**
 * 结果枚举
 *
 * @author longjun
 * @date 2023/04/11
 */
@AllArgsConstructor
public enum ResultEnum {
    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 参数校验失败
     */
    PARAMETER_INVALID(400, "参数校验失败"),
    /**会话已过期*/
    SESSION_DUED(401, "需要登录后操作"),
    /**
     * 必须登录
     */
    NEED_LOGIN(401, "需要登录后操作"),
    /**用户未注册*/
    NOT_REGISTERED(403, "账号未注册"),
    /**
     * 代码异常
     */
    CODE_EXCEPTION(500, "后端代码内部异常"),
    /**
     * 参数错误
     */
    PARAMETER_ERROR(999, "前端入参异常"),

    /**
     * 登陆失败
     */
    LOGIN_ERROR(501, "用户名或密码错误"),

    /**
     * 失败
     */
    FAIL(501, "后端代码异常"),
    /**系统异常*/
    SYSTEM_EROOR(501, "系统异常"),
    /**
     * 空点
     */
    NULL_POINT(1000, "空指针异常"),
    /**
     * 指数误差
     */
    OUT_OF_INDEX_ERROR(1001, "索引越界异常"),
    /**
     * 模型零
     */
    MODEL_NULL(1002, "前端入参实体的实体为空"),
    /**
     * 数据库错误
     */
    DATABASE_ERROR(1003, "数据库异常"),
    /**
     * 身份验证错误
     */
    AUTHENTICATION_ERROR(1004, "身份验证异常"),
    /**
     * 逻辑错误
     */
    LOGIC_ERROR(1005, "业务逻辑异常"),
    /**
     * 类没有找到
     */
    CLASS_NOT_FOUND(1006, "类未找到异常"),
    /**
     * sql异常
     */
    SQL_EXCEPTION(1007, "sql语句异常"),
    /**
     * io例外
     */
    IO_EXCEPTION(1008, "io异常"),
    /**
     * json解析错误
     */
    JSON_PARSE_ERROR(1009, "json转换异常"),

    NUMBER_FORMAT_ERROR(1010, "String转换为数字错误"),
    /**
     * 更新失败
     */
    UPDATE_FAIL(1011, "更新失败"),

    /**
     * 发送POST错误
     */
    SEND_POST_ERROR(1012, "发送POST请求异常"),
    /**
     * 短信发送错误
     */
    SMS_SEND_ERROR(1013, "短信发送失败"),
    /**
     * 账号已注册过
     */
    REGISTERED_ALREADY(422, "账号已注册过"),
    /**
     * 验证码已过期
     */
    CODE_DUED(422, "验证码已过期"),;

	/**
     * 状态码
     */
    private Integer code;

    public Integer getCode() {
        return code;
    }

    ResultEnum(Integer code) {
        this.code = code;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

}
 
 