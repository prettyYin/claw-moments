package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.dto.*;
import com.moments.claw.domain.vo.ArticleVo;
import com.moments.claw.domain.vo.CommunityArticleVo;
import com.moments.claw.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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

    private final ArticleService articleService;

    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @ApiOperation(value = "首页帖子列表")
    @GetMapping("/list")
    public TableDataInfo<?> list(IndexArticleDto dto) {
        startPage();
        List<ArticleVo> list = articleService.indexArticleList(dto);
        return getDataTable(list);
    }

    @ApiOperation("查询社区帖子列表")
    @GetMapping("/community/list")
    public TableDataInfo<CommunityArticleVo> communityList(@Valid CommunityArticleDto dto) {
        return articleService.communityList(dto);
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
        ArticleVo article = articleService.viewArticleDetailById(id);
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
     * 批量删除数据
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
     * 删除数据
     *
     * @param dto 删除对象
     * @return 删除结果
     */
    @ApiOperation(value = "删除数据")
    @DeleteMapping("/trash")
    public R<?> delete(@ApiParam(name = "id", required = true) @RequestBody @Valid ArticleDeleteDto dto) {
        return R.success(articleService.removeById(dto.getId()));
    }

    @ApiOperation(value = "我的帖子列表")
    @GetMapping("/my-list")
    public TableDataInfo<?> myPetList(MyArticlePageQueryDto pageQuery) {
        return articleService.myArticleList(pageQuery);
    }

    @ApiOperation(value = "我发布的帖子详情")
    @GetMapping("/my-view")
    public R<?> myPetList(Long articleId) {
        return R.success(articleService.myArticleView(articleId));
    }

    /**
     * 活动跟帖
     */
    @ApiOperation(value = "活动跟帖")
    @PostMapping("/form")
    public R<?> form(@RequestBody @Validated SendArticleFromActivityDto dto) {
        articleService.form(dto);
        return R.success();
    }

    /**
     * 社区发帖或编辑帖子
     */
    @ApiOperation(value = "社区发帖或编辑帖子")
    @PostMapping("/community/form")
    public R<?> communityForm(@RequestBody @Validated SendOrUpdateArticleFromCommunityDto dto) {
        articleService.communityForm(dto);
        return R.success();
    }

    /**
     * 点赞
     */
    @ApiOperation(value = "点赞")
    @GetMapping("/toggleLike")
    public R<?> toggleLike(@RequestParam("articleId") Long articleId) {
        articleService.toggleLike(articleId);
        return R.success();
    }

    /**
     * 查看数+1
     */
    @ApiOperation(value = "查看数+1")
    @GetMapping("/incrView")
    public R<?> incrView(@RequestParam("articleId") Long articleId) {
        articleService.incrView(articleId);
        return R.success();
    }

    @ApiOperation(value = "分享帖子")
    @GetMapping("/share")
    public R<?> share(@RequestParam("id") Long id) {
        return R.success(articleService.share(id));
    }
}

