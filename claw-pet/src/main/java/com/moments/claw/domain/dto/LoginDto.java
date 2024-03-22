package com.moments.claw.domain.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode
public class LoginDto {

	private String gruop;

	@Pattern(regexp = "^1[34578]\\d{9}$",message = "请输入正确的手机号")
	private String mobile;

	private String password;

	private String smsCode;
}
