package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Comment;
import com.ruoyi.system.domain.vo.CommentVo;

import java.util.List;

/**
 * 评论表(Comments)表服务接口
 *
 * @author chandler
 * @since 2024-03-17 17:31:56
 */
public interface CommentService extends IService<Comment> {

    List<CommentVo> selectCommentList(Comment comment);

    void deleteBatchByIds(List<String> ids);
}