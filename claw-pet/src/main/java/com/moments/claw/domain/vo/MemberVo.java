package com.moments.claw.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class MemberVo {

	@ApiModelProperty(name = "name", value = "会员名称")
	private String name;

	@ApiModelProperty(name = "icon", value = "会员图标")
	private String icon;

	@ApiModelProperty(name = "level", value = "会员等级（0：非会员；1：大众会员；2：白银会员；3：黄金会员；4：铂金会员；5：钻石会员；6：尊贵会员；7：豪华会员）")
	private Integer level;

	@ApiModelProperty(name = "cardId", value = "身份证")
	private String cardId;

	@ApiModelProperty(name = "phoneNumber", value = "手机号")
	private String phoneNumber;

	@ApiModelProperty(name = "balance", value = "余额")
	private Double balance;

	@ApiModelProperty(name = "integral", value = "积分")
	private Long integral;

	@ApiModelProperty(name = "status", value = "状态（-1异常，1正常，2冻结，3注销）")
	private Integer status;
}
