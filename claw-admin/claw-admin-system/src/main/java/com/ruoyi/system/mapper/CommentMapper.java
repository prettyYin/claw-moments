package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moments.claw.domain.base.entity.Comment;
import com.ruoyi.system.domain.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论表(Comments)表数据库访问层
 *
 * @author chandler
 * @since 2024-03-17 17:32:24
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVo> selectCommentList(Comment comment);

    void deleteBatchByIds(@Param("ids") List<String> ids);
}