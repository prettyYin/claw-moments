package com.moments.claw.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

	/**验证码*/
	private String code;

	/**
	 * 登陆方式
	 */
	private String loginMethod;

	/**
	 * 手机号
	 */
	@Pattern(regexp = "^1[34578]\\d{9}$",message = "手机号格式不正确")
	@NotBlank(message = "手机号不能为空")
	private String mobile;


	/**
	 * 昵称
	 */
	@NotBlank(message = "昵称不能为空")
	@Size(min = 4, max = 16,message = "请输入长度为4-16位的昵称")
	private String nickName;
	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 18,message = "请输入长度为6-18位的密码")
	private String password;

	/**
	 * 确认密码
	 */
	@JsonProperty("password_repetition")
	@NotBlank(message = "请保持两次输入的密码一致")
	private String passwordRepetition;

	@JsonProperty("promoter_code")
	private String promoterCode;

}
