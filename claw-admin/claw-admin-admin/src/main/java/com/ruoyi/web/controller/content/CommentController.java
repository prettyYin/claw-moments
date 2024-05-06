package com.ruoyi.web.controller.content;

import com.moments.claw.domain.base.entity.Comment;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Api(tags = "")
@RestController
@RequestMapping("/content/comment")
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
    public TableDataInfo selectAll(Comment comment) {
        startPage();
        return getDataTable(commentService.selectCommentList(comment));
    }

    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathVariable("id") String id) {
        if (id == null) {
            throw new ServiceException("请选择要删除的评论");
        }
        List<String> ids = Arrays.asList(id.split(","));
        commentService.deleteBatchByIds(ids);
        return success();
    }
}