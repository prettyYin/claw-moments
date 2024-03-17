package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Comments;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.mapper.CommentsMapper;
import com.moments.claw.service.CommentsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comments)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 17:31:56
 */
@Service("commentsService")
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

	@Override
	public List<Comments> getRootComments() {
		List<Comments> rootComments = list(new LambdaQueryWrapper<Comments>()
				.eq(Comments::getParentId, GlobalConstants.ROOT_PARENT_ID)
				.eq(Comments::getStatus, GlobalConstants.NORMAL_STATUS)
		);
		return rootComments;
	}
}

