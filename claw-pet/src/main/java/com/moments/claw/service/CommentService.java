package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Comment;
import com.moments.claw.domain.dto.CommentSendDto;

import java.util.List;


/**
 * 评论表(Comments)表服务接口
 *
 * @author chandler
 * @since 2024-03-17 17:31:56
 */
public interface CommentService extends IService<Comment> {

	List<Comment> getRootComments(Long articleId);

	void form(CommentSendDto dto);
}
