package com.moments.claw.controller;

import com.moments.claw.biz.IntegralItemBiz;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.moments.claw.service.IntegralItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import java.io.Serializable;
import java.util.List;

/**
 * 积分商品表(IntegralItem)表控制层
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
@Api(tags = "IntegralItemController控制层", value = "/integralItem")
@RestController
@RequiredArgsConstructor
@RequestMapping("/integralItem")
public class IntegralItemController extends BaseController {

    private final IntegralItemBiz integralItemBiz;
    private final IntegralItemService integralItemService;

    /**
     * 获取积分物品列表
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public R<?> integralItemList() {
        return R.success(integralItemBiz.integralItemList());
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
        return R.success(integralItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param integralItem 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody IntegralItem integralItem) {
        return R.success(integralItemService.save(integralItem));
    }

    /**
     * 修改数据
     *
     * @param integralItem 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody IntegralItem integralItem) {
        return R.success(integralItemService.updateById(integralItem));
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
        return R.success(integralItemService.removeByIds(idList));
    }
}

