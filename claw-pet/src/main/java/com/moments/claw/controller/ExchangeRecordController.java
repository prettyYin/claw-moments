package com.moments.claw.controller;


import com.moments.claw.biz.ExchangeRecordBiz;
import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.EquipNowDto;
import com.moments.claw.service.ExchangeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * 积分兑换商品记录表(ExchangeRecord)表控制层
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
@Api(tags = "ExchangeRecordController控制层", value = "/exchangeRecord")
@RestController
@RequiredArgsConstructor
@RequestMapping("/exchangeRecord")
public class ExchangeRecordController extends BaseController {

    private final ExchangeRecordBiz exchangeRecordBiz;
    private final ExchangeRecordService exchangeRecordService;

    /**
     * 我的兑换记录列表
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public R<?> listMyRecord() {
        return R.success(exchangeRecordService.listRecordByUserId(SecurityUtils.getUserId()));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("/{id}")
    public R<?> selectOne(@ApiParam(name = "id", value = "id", required = true) @PathVariable Serializable id) {
        return R.success(exchangeRecordService.getById(id));
    }

    /**
     * 商品兑换
     *
     * @param exchangeRecord 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping("/exchange")
    public R<?> addExchangeRecord(@RequestBody @Valid ExchangeRecord exchangeRecord) {
        exchangeRecordBiz.addExchangeRecord(exchangeRecord);
        return R.success();
    }

    /**
     * 修改数据
     *
     * @param exchangeRecord 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody ExchangeRecord exchangeRecord) {
        return R.success(exchangeRecordService.updateById(exchangeRecord));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @ApiOperation(value = "删除数据")
    @DeleteMapping
    public R<?> delete(@ApiParam(name = "idList", value = "id数组", required = true) @RequestParam("idList") List<Long> idList) {
        return R.success(exchangeRecordService.removeByIds(idList));
    }

    @ApiOperation(value = "获取最新一条装备的数据")
    @GetMapping("/latestJson")
    public R<?> latestJson() {
        return R.success(exchangeRecordService.latestJson());
    }

    @ApiOperation(value = "立即装备上背景图")
    @PutMapping("/equipNow")
    public R<?> equipNow(@RequestBody @Valid EquipNowDto dto) {
        exchangeRecordService.equipNow(dto);
        return R.success();
    }

}

