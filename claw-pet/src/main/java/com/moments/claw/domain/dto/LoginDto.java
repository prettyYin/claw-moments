package com.moments.claw.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode
public class LoginDto {

	private String gruop;

	@Pattern(regexp = "^1[34578]\\d{9}$",message = "手机号格式不正确")
	@NotBlank(message = "手机号不能为空")
	private String mobile;

	@NotBlank(message = "密码不能为空")
	@Size(min = 6, max = 18,message = "请输入长度为6-18位的密码")
	private String password;

	@JsonProperty("code")
	@NotBlank(message = "密码不能为空")
	@Size(min = 4, max = 4,message = "请输入长度为4位的验证码")
	private String smsCode;
}
