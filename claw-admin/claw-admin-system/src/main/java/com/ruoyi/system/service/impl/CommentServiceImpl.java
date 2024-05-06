package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Comment;
import com.ruoyi.system.domain.vo.CommentVo;
import com.ruoyi.system.mapper.CommentMapper;
import com.ruoyi.system.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comments)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 17:31:56
 */
@Service("adminCommentsService")
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<CommentVo> selectCommentList(Comment comment) {
        return commentMapper.selectCommentList(comment);
    }
}