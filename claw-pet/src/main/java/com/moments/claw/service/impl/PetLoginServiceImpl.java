package com.moments.claw.service.impl;

import com.moments.claw.domain.common.domain.LoginUser;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.exception.CustomException;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.JwtUtil;
import com.moments.claw.domain.dto.LoginDto;
import com.moments.claw.domain.entity.PetLoginDomain;
import com.moments.claw.service.PetLoginService;
import org.omg.CORBA.SystemException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class PetLoginServiceImpl implements PetLoginService {

	@Resource
	private RedisService redisService;
	@Resource
	private AuthenticationManager authenticationManager;

	@Override
	public R<?> loginByPass(LoginDto loginDto) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDto.getMobile(), loginDto.getPassword());
		Authentication authenticate = authenticationManager.authenticate(authentication);
		// 认证是否通过
		if (Objects.isNull(authenticate)) {
			throw new CustomException(ResultEnum.LOGIN_ERROR);
		}
		// 通过jwt生成token，userId 和 userInfo 存入redis中
		LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
		String userId = loginUser.getUser().getId().toString();
		String token = JwtUtil.createJWT(userId);
		PetLoginDomain loginUserInfoVo = CopyBeanUtils.copyBean(loginUser.getUser(), PetLoginDomain.class);
		loginUserInfoVo.setAccessToken(token);
		loginUserInfoVo.setRefreshToken(token);
		redisService.set("claw-pet-login:" + userId,loginUser);
		return R.success(loginUserInfoVo);
	}

	@Override
	public R<?> loginBySms(LoginDto loginDto) {
		return null;
	}
}
