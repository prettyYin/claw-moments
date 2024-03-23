package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Comment;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.mapper.CommentMapper;
import com.moments.claw.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comments)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 17:31:56
 */
@Service("commentsService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

	@Override
	public List<Comment> getRootComments() {
		List<Comment> rootComments = list(new LambdaQueryWrapper<Comment>()
				.eq(Comment::getParentId, GlobalConstants.ROOT_PARENT_ID)
				.eq(Comment::getStatus, GlobalConstants.NORMAL_STATUS)
		);
		return rootComments;
	}
}

