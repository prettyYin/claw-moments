package com.moments.claw.controller;

import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.dto.LoginDto;
import com.moments.claw.service.PetLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PetLoginController {

	@Resource
	private PetLoginService petLoginService;

	@PostMapping("/site/login")
	public R<?> login(@Validated @RequestBody LoginDto loginDto) {
		if (StringUtils.isNotBlank(loginDto.getPassword())) {
			return petLoginService.loginByPass(loginDto);
		}
		if (StringUtils.isNotBlank(loginDto.getSmsCode())) {
			return petLoginService.loginBySms(loginDto);
		}
		return R.fail(ResultEnum.AUTHENTICATION_ERROR.getCode(), ResultEnum.AUTHENTICATION_ERROR.getMsg());
	}
}
