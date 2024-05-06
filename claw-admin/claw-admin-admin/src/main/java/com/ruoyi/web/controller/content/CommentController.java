package com.ruoyi.web.controller.content;

import com.moments.claw.domain.base.entity.Comment;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.service.CommentService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}