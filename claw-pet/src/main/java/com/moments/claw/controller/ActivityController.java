package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;
import com.moments.claw.domain.dto.ActivityDtoPageQuery;
import com.moments.claw.domain.dto.MyActivityPageQueryDto;
import com.moments.claw.service.ActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
@RequiredArgsConstructor
public class ActivityController extends BaseController {
    /**
     * 服务对象
     */
    private final ActivityService activityService;

    /**
     * 活动列表
     *
     * @return 所有数据
     */
    @ApiOperation(value = "分页查询活动列表")
    @GetMapping("/list")
    public TableDataInfo<?> list(@Validated ActivityDtoPageQuery pageQuery) {
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
    public R<?> getActivityById(@ApiParam(name = "id", value = "id", required = true) @NotBlank(message = "活动id不能为空") @PathVariable Serializable id) {
        return R.success(activityService.getActivityById(id));
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
    public TableDataInfo<?> articleList(@Valid ActivityArticleDtoPageQuery pageQuery) {
        return activityService.articleList(pageQuery);
    }

    /**
     * 切换点赞状态
     */
    @PutMapping("/toggleLike")
    public R<?> toggleLike(@RequestBody ActivityUser params) {
        activityService.toggleLike(params);
        return R.success();
    }

    /**
     * 浏览量+1
     */
    @GetMapping("/incrViewCount")
    public R<?> incrViewCount(@RequestParam("id") Long id) {
        activityService.incrViewCount(id);
        return R.success();
    }

    /**
     * 报名活动
     */
    @GetMapping("/apply")
    public R<?> apply(@RequestParam("activityId") Long activityId) {
        Long userId = SecurityUtils.getUserId();
        activityService.apply(userId, activityId);
        return R.success();
    }

    /**
     * 我的活动
     */
    @GetMapping("/m-list")
    public TableDataInfo<?> myActivityList(MyActivityPageQueryDto dto) {
        return activityService.myActivityList(dto);
    }

}

