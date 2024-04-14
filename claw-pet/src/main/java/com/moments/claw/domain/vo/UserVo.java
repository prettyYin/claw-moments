package com.moments.claw.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVo {

	@ApiModelProperty(name = "nickname", value = "网名")
	private String nickname;

	@ApiModelProperty(name = "gender", value = "性别（0：保密；1：男；2：女）")
	private Integer gender;

	@ApiModelProperty(name = "weixin", value = "微信号")
	private String weixin;

	@ApiModelProperty(name = "qq", value = "qq号")
	private String qq;

	@JsonIgnore
	@ApiModelProperty(name = "avatarId", value = "头像图片id")
	private String avatarId;

	@ApiModelProperty(name = "avatar", value = "头像图片")
	private String avatar;

	@ApiModelProperty(name = "follow", value = "关注数")
	private Long follow;

	@ApiModelProperty(name = "fans", value = "粉丝数")
	private Long fans;
}
