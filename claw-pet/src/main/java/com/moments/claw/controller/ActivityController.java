package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;
import com.moments.claw.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 活动表(Activities)表控制层
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Api(tags = "ActivityController控制层", value = "/activity")
@RestController
@RequestMapping("/activity")
public class ActivityController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;

    /**
     * 活动推荐列表
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> list(@Validated PageQuery pageQuery) {
        return activityService.recommendList(pageQuery);
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
        return R.success(activityService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param activity 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody Activity activity) {
        return R.success(activityService.save(activity));
    }

    /**
     * 修改数据
     *
     * @param activity 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody Activity activity) {
        return R.success(activityService.updateById(activity));
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
        return R.success(activityService.removeByIds(idList));
    }

    /**
     * 根据活动id获取参与活动的文章id
     */
    @GetMapping("/articleList")
    public TableDataInfo<?> articleList(@Validated ActivityArticleDtoPageQuery pageQuery) {
        return activityService.articleList(pageQuery);
    }
}

