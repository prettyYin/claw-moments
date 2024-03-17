package com.moments.claw.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.moments.claw.domain.base.entity.Comments;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.controller.BaseController;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.service.CommentsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("comments")
public class CommentsController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private CommentsService commentsService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    public TableDataInfo<?> selectAll() {
        return getDataTable(commentsService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return R.success(commentsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param comments 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody Comments comments) {
        return R.success(commentsService.save(comments));
    }

    /**
     * 修改数据
     *
     * @param comments 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody Comments comments) {
        return R.success(commentsService.updateById(comments));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return R.success(commentsService.removeByIds(idList));
    }

    /**
     * 根评论
     * @return
     */
    @GetMapping("/rootComments")
    public R<?> rootComments() {
        List<Comments> rootComments = commentsService.getRootComments();
        return R.success(rootComments);
    }
}

