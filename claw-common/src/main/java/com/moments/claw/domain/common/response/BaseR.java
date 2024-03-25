package com.moments.claw.domain.common.response;

import com.moments.claw.domain.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("响应信息主体")
public class BaseR implements IResponse {
    @ApiModelProperty("编码")
    private Integer code = 0;
    @ApiModelProperty("提示信息")
    private String msg;
    @ApiModelProperty("请求id")
    private String requestId;

    protected boolean canEqual(Object other) {
        return other instanceof BaseR;
    }

    public String toString() {
        return "BaseR(code=" + this.getCode() + ", msg=" + this.getMsg() + ", requestId=" + this.getRequestId() + ")";
    }
}
