package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.Comment;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.dto.CommentSendDto;
import com.moments.claw.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * 评论表(Comments)表控制层
 *
 * @author chandler
 * @since 2024-03-17 17:31:17
 */
@Api(tags = "")
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        return getDataTable(commentService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return R.success(commentService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param comment 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Comment comment) {
        return R.success(commentService.save(comment));
    }

    /**
     * 修改数据
     *
     * @param comment 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Comment comment) {
        return R.success(commentService.updateById(comment));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return R.success(commentService.removeByIds(idList));
    }

    /**
     * 根评论
     */
    @GetMapping("/rootComments")
    public R<?> rootComments() {
        List<Comment> rootComments = commentService.getRootComments(null);
        return R.success(rootComments);
    }

    /**
     * 留言
     */
    @PostMapping("/form")
    public R<?> form(@RequestBody @Valid CommentSendDto dto) {
        commentService.form(dto);
        return R.success();
    }

    /**
     * 评论点赞
     */
    @GetMapping("/like")
    public R<?> toggleLike(@RequestParam("id") Long id) {
        commentService.toggleLike(id);
        return R.success();
    }
}

