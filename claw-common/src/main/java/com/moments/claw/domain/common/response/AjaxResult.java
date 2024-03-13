package com.moments.claw.domain.common.response;

import com.moments.claw.domain.common.enums.ResultEnum;

import java.util.HashMap;
import java.util.Objects;

public class AjaxResult extends HashMap<String, Object> implements IResponse {
    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

    public AjaxResult() {
    }

    public AjaxResult(int code, String msg) {
        super.put("code", code);
        super.put("msg", msg);
    }

    public AjaxResult(int code, String msg, Object data) {
        super.put("code", code);
        super.put("msg", msg);
        if (Objects.nonNull(data)) {
            super.put("data", data);
        }

    }

    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static AjaxResult success() {
        return success("操作成功");
    }

    public static AjaxResult success(Object data) {
        return success("操作成功", data);
    }

    public static AjaxResult success(String msg) {
        return success(msg, (Object)null);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(ResultEnum.SUCCESS.getCode(), msg, data);
    }

    public static AjaxResult error() {
        return error("操作失败");
    }

    public static AjaxResult error(String msg) {
        return error(msg, (Object)null);
    }

    public static AjaxResult error(String msg, Object data) {
        return new AjaxResult(ResultEnum.FAIL.getCode(), msg, data);
    }

    public static AjaxResult error(int code, String msg) {
        return new AjaxResult(code, msg, (Object)null);
    }

    public void setRequestId(String id) {
        this.put((String)"requestId", id);
    }

    public String getRequestId() {
        return (String)this.get("requestId");
    }
}
