package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Fans;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.FansDtoPageQuery;
import com.moments.claw.domain.vo.FansVo;
import com.moments.claw.service.FansService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 用户粉丝表(Fans)表控制层
 *
 * @author chandler
 * @since 2024-04-05 01:35:18
 */
@Api(tags = "FansController控制层", value = "/fans")
@RestController
@RequestMapping("/fans")
public class FansController extends BaseController {

    @Resource
    private FansService fansService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> list(FansDtoPageQuery pageQuery) {
        Long userId = SecurityUtils.getUserId();
        List<FansVo> voList = fansService.fansList(userId);
        return PaginationUtil.handPaged(voList, pageQuery.getPageSize(), pageQuery.getPageNum());
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
        return R.success(fansService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param fans 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody Fans fans) {
        return R.success(fansService.save(fans));
    }

    /**
     * 修改数据
     *
     * @param fans 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody Fans fans) {
        return R.success(fansService.updateById(fans));
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
        return R.success(fansService.removeByIds(idList));
    }

    @ApiOperation(value = "粉丝数")
    @GetMapping("/count")
    public R<?> count() {
        Long userId = SecurityUtils.getUserId();
        Integer count = fansService.fansCount(userId);
        return R.success(count);
    }
}

