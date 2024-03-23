package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.service.ActivityArticleService;
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
 * (ActivityArticle)表控制层
 *
 * @author chandler
 * @since 2024-03-24 03:40:49
 */
@Api(tags = "ActivityArticleController控制层", value = "/activityArticle")
@RestController
@RequestMapping("/activityArticle")
public class ActivityArticleController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityArticleService activityArticleService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        startPage();
        return getDataTable(activityArticleService.list());
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
        return R.success(activityArticleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param activityArticle 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody ActivityArticle activityArticle) {
        return R.success(activityArticleService.save(activityArticle));
    }

    /**
     * 修改数据
     *
     * @param activityArticle 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody ActivityArticle activityArticle) {
        return R.success(activityArticleService.updateById(activityArticle));
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
        return R.success(activityArticleService.removeByIds(idList));
    }
}

