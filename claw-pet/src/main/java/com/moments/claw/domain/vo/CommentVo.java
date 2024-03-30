package com.moments.claw.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class CommentVo {

	@ApiModelProperty(name = "id", value = "主键")
	private Long id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Long userId;

	@ApiModelProperty(name = "content", value = "评论内容")
	private String content;

	@ApiModelProperty(name = "thumbCount", value = "点赞数")
	private Long thumbCount;

	@ApiModelProperty(name = "createdAt", value = "发表时间")
	private Date createdAt;

	@ApiModelProperty(name = "user", value = "用户信息")
	private UserCommentVo user;

}
