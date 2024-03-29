package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.dto.SendAritcleDto;
import com.moments.claw.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * (Article)表控制层
 *
 * @author chandler
 * @since 2024-03-17 19:44:32
 */
@Api(tags = "ArticleController控制层", value = "/article")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "查询所有数据")
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        startPage();
        return getDataTable(articleService.list());
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
        return R.success(articleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param article 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody Article article) {
        return R.success(articleService.save(article));
    }

    /**
     * 修改数据
     *
     * @param article 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody Article article) {
        return R.success(articleService.updateById(article));
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
        return R.success(articleService.removeByIds(idList));
    }

    /**
     * 活动跟帖
     */
    @ApiOperation(value = "活动跟帖")
    @PostMapping("/form")
    public R<?> form(@RequestBody @Validated SendAritcleDto dto) {
        articleService.form(dto);
        return R.success();
    }
}

