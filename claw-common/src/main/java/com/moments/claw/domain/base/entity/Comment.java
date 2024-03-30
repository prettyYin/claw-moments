package com.moments.claw.domain.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.moments.claw.domain.BaseEntity;

/**
 * 评论表(ClawComments)表实体类
 *
 * @author chandler
 * @since 2024-03-17 17:29:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("claw_comment")
@ApiModel(value = "claw_comment实体类", description = "用于存储传输claw_comment信息")
public class Comment extends BaseEntity {

	@TableId(type = IdType.AUTO)
	@ApiModelProperty(name = "id", value = "主键", hidden = true)
	private Long id;

	@ApiModelProperty(name = "userId", value = "用户id")
	private Long userId;

	@ApiModelProperty(name = "articleId", value = "文章id")
	private Long articleId;

	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(name = "parentId", value = "父评论id")
	private Long parentId;

	@ApiModelProperty(name = "content", value = "评论内容")
	private String content;

	@ApiModelProperty(name = "toCommentUserid", value = "回复评论的用户id")
	private Long toCommentUserid;

	@ApiModelProperty(name = "toCommentId", value = "回复的评论id")
	private Long toCommentId;

	@ApiModelProperty(name = "thumbCount", value = "点赞数")
	private Long thumbCount;

	@ApiModelProperty(name = "status", value = "状态（0异常，1正常）")
	private Integer status;
}
