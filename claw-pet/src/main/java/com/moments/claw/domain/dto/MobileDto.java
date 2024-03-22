package com.moments.claw.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MobileDto {
	/**
	 * 手机号
	 */
	@Pattern(regexp = "^1[34578]\\d{9}$",message = "手机号格式不正确")
	@NotBlank(message = "手机号不能为空")
	private String mobile;
	/**
	 * 用途
	 */
	@NotBlank(message = "验证码不能为空")
	private String usage;
}
