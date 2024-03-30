package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.dto.ArticleDto;
import com.moments.claw.domain.dto.SendArticleDto;
import com.moments.claw.domain.vo.ArticleVo;
import com.moments.claw.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * 宠物表(Pet)表控制层
 *
 * @author chandler
 * @since 2024-03-11 22:40:59
 */
@Api(tags = "ArticleController控制层", value = "/article")
@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController extends BaseController {
    /**
     * 服务对象
     */
    private final ArticleService articleService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "宠物列表")
    @GetMapping("/list")
    public TableDataInfo<?> list(ArticleDto dto) {
        startPage();
        List<ArticleVo> list = articleService.petList(dto);
        return getDataTable(list);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "通过主键查询单条数据")
    @GetMapping("/view/{id}")
    public R<?> viewDetailById(@ApiParam(name = "id", value = "id", required = true) @PathVariable Serializable id) {
        ArticleVo article = articleService.viewDetailById(id);
        return R.success(article);
    }

    /**
     * 新增数据
     *
     * @param pet 实体对象
     * @return 新增结果
     */
    @ApiOperation(value = "新增数据")
    @PostMapping
    public R<?> insert(@RequestBody Article pet) {
        return R.success(articleService.save(pet));
    }

    /**
     * 修改数据
     *
     * @param pet 实体对象
     * @return 修改结果
     */
    @ApiOperation(value = "修改数据")
    @PutMapping
    public R<?> update(@RequestBody Article pet) {
        return R.success(articleService.updateById(pet));
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

    @ApiOperation(value = "我的宠物列表")
    @GetMapping("/m-list")
    public R<?> myPetList(PageQuery pageQuery) {
        List<ArticleVo> myPetList = articleService.myPetList(pageQuery);
        return R.success(myPetList);
    }

    /**
     * 活动跟帖
     */
    @ApiOperation(value = "活动跟帖")
    @PostMapping("/form")
    public R<?> form(@RequestBody @Validated SendArticleDto dto) {
        articleService.form(dto);
        return R.success();
    }
}

