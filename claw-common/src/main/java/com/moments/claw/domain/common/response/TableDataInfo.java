package com.moments.claw.domain.common.response;

import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel("表格分页数据对象")
public class TableDataInfo<T> extends BaseR {
    @ApiModelProperty("总记录数")
    private long total;
    @ApiModelProperty("列表数据")
    private List<T> rows;
    private int pageNum;
    private int pageSize;
    private int currPage;

    public TableDataInfo() {
    }

    public TableDataInfo(PageQuery page) {
        this.currPage = page.getPageNum();
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.setCode(ResultEnum.SUCCESS.getCode());
    }

    public TableDataInfo(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public TableDataInfo(List<T> list, int total) {
        this.rows = list;
        this.total = (long)total;
    }

    public String toString() {
        return "TableDataInfo(total=" + this.getTotal() + ", rows=" + this.getRows() + ", pageNum=" + this.getPageNum() + ", pageSize=" + this.getPageSize() + ", currPage=" + this.getCurrPage() + ")";
    }
    protected boolean canEqual(Object other) {
        return other instanceof TableDataInfo;
    }

}
