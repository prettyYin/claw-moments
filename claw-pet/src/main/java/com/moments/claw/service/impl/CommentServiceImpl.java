package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Comment;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.CommentSendDto;
import com.moments.claw.mapper.CommentMapper;
import com.moments.claw.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 评论表(Comments)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 17:31:56
 */
@Service("commentsService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

	@Override
	public List<Comment> getRootComments(Long articleId) {
		return list(new LambdaQueryWrapper<Comment>()
				.eq(Comment::getParentId, GlobalConstants.ROOT_PARENT_ID)
				.eq(Comment::getStatus, GlobalConstants.NORMAL_STATUS)
				.eq(Objects.nonNull(articleId), Comment::getArticleId, articleId)
		);
	}

	@Override
	public void form(CommentSendDto dto) {
		Comment comment = CopyBeanUtils.copyBean(dto, Comment.class);
		comment.setUserId(SecurityUtils.getUserId());
		save(comment);
	}

	@Override
	public void toggleLike(Long id) {
		Comment comment = getById(id);
		Optional.ofNullable(comment).ifPresent(c -> lambdaUpdate()
				.set(Comment::getThumbCount, c.getThumbCount() + 1)
				.eq(Comment::getId, id)
				.update());
	}
}

