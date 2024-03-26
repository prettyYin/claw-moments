package com.moments.claw.domain.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PageQuery {
    @ApiModelProperty(value = "第n页",required = true)
    @NotBlank(message = "页码不能为空")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "每页显示数量", required = true)
    @NotBlank(message = "页码不能为空")
    private Integer pageSize = 10;
    @ApiModelProperty("排序字段")
    @Pattern(
        regexp = "^[a-zA-Z_]\\w*$",
        message = "排序字段包含非法字符"
    )
    private String sortField;
    @Pattern(
        regexp = "^asc$|^desc$",
        message = "排序仅支持：asc 或 desc"
    )
    @ApiModelProperty("排序方式：asc, desc")
    private String sortOrder;
    public static final String PAGE_NUM = "pageNum";
    public static final String PAGE_SIZE = "pageSize";
    public static final String ORDER_BY_COLUMN = "orderByColumn";
    public static final String IS_ASC = "isAsc";

    public String toString() {
        return "PageQuery(pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ", sortField=" + this.getSortField() + ", sortOrder=" + this.getSortOrder() + ")";
    }
}
