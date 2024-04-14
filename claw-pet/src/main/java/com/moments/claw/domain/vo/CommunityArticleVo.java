package com.moments.claw.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CommunityArticleVo {

	@ApiModelProperty(name = "id", value = "主键", hidden = true)
	private Long id;

	@ApiModelProperty(name = "title", value = "标题")
	private String title;

	@JsonIgnore
	@ApiModelProperty(name = "userId", value = "用户id")
	private Long userId;

	@ApiModelProperty(name = "avatar", value = "头像")
	private String avatar;

	@ApiModelProperty(name = "content", value = "内容")
	private String content;

	@ApiModelProperty(name = "isTop", value = "是否置顶（0：否；1：是）")
	private Integer isTop;

	@ApiModelProperty(name = "userName", value = "发帖人姓名")
	private String userName;

	@ApiModelProperty(name = "type", value = "类型（1：领养；2：丢失；3：关注；4：科普；5：救助；6：闲置；7：其他）")
	private Integer type;

	@ApiModelProperty(name = "cate", value = "种类（1：猫；2：狗；3：其他）")
	private Integer cate;

	@ApiModelProperty(name = "view", value = "点击次数")
	private Long view;

	@ApiModelProperty(name = "praise", value = "点赞数")
	private Long praise;

	@ApiModelProperty(name = "dislike", value = "倒赞数")
	private Long dislike;

	@ApiModelProperty(name = "comment", value = "评论数")
	private Long comment;

	@ApiModelProperty(name = "share", value = "分享数")
	private Long share;

	@ApiModelProperty(name = "status", value = "状态（0异常，1正常，2禁用）")
	private Integer status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "创建时间")
	private Date createdAt;
}
