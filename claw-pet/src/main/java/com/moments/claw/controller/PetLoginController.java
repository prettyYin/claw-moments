package com.moments.claw.controller;

import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.domain.dto.LoginDto;
import com.moments.claw.domain.dto.MobileDto;
import com.moments.claw.domain.dto.RegisterDto;
import com.moments.claw.service.PetLoginService;
import com.moments.claw.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

@RestController
public class PetLoginController {

	@Resource
	private PetLoginService petLoginService;
	@Resource
	private UserService userService;


	/**
	 * 校验token合法性
	 */
	@PostMapping("/site/verify-access-token")
	public R<?> verifyToken(@RequestBody String token) {
		return R.success(petLoginService.verifyToken(token));
	}

	@PostMapping("/site/login")
	public R<?> login(@Validated @RequestBody LoginDto loginDto) {
		if (StringUtils.isNotBlank(loginDto.getPassword())) {
			return R.success(petLoginService.loginByPass(loginDto));
		}
		if (StringUtils.isNotBlank(loginDto.getSmsCode())) {
			return R.success(petLoginService.loginBySms(loginDto));
		}
		return R.fail(ResultEnum.AUTHENTICATION_ERROR.getCode(), ResultEnum.AUTHENTICATION_ERROR.getMsg());
	}

	@ApiOperation("获取验证码")
	@PostMapping("/site/sms-code")
	public R<?> smsCode(@Validated @RequestBody MobileDto mobileDto) {
		// 是否已注册
		User user = userService.getByMobile(mobileDto.getMobile());
		if (Objects.nonNull(user)) {
			return R.fail(ResultEnum.REGISTERED_ALREADY.getCode(),ResultEnum.REGISTERED_ALREADY.getMsg());
		}
		return R.success(userService.smsCode(mobileDto));
	}

	@PostMapping("/site/register")
	public R<?> register(@Validated @RequestBody RegisterDto registerDto) {

		if (!registerDto.getPassword().equals(registerDto.getPasswordRepetition())) {
			return R.fail("请保持两次输入的密码一致");
		}
		return R.success(petLoginService.register(registerDto));
	}

	@PostMapping("/site/logout")
	public R<?> logout() {
		petLoginService.logout();
		return R.success();
	}
}
