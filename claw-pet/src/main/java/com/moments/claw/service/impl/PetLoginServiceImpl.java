package com.moments.claw.service.impl;

import com.moments.claw.common.constant.PetConstants;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.common.domain.LoginUser;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.JwtUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.LoginDto;
import com.moments.claw.domain.dto.RegisterDto;
import com.moments.claw.domain.dto.VerifyTokenDto;
import com.moments.claw.domain.entity.PetLoginDomain;
import com.moments.claw.domain.vo.LoginUserVo;
import com.moments.claw.service.PetLoginService;
import com.moments.claw.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

@Service
public class PetLoginServiceImpl implements PetLoginService {

	@Resource
	private PasswordEncoder passwordEncoder;
	@Resource
	private RedisService redisService;
	@Resource
	private AuthenticationManager authenticationManager;
	@Resource
	private UserService userService;

	@Override
	public PetLoginDomain loginByPass(LoginDto loginDto) {
		// 默认以手机号注册作为用户名
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDto.getMobile(), loginDto.getPassword());
		Authentication authenticate = authenticationManager.authenticate(authentication);
		// 认证是否通过
		if (Objects.isNull(authenticate)) {
			throw new BizException(ResultEnum.SUCCESS.getCode(),ResultEnum.LOGIN_ERROR.getMsg());
		}
		// 通过jwt生成token，userId 和 userInfo 存入redis中
		LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
		String userId = loginUser.getUser().getId().toString();
		String token = JwtUtil.createJWT(userId);
		PetLoginDomain loginUserInfoVo = CopyBeanUtils.copyBean(loginUser.getUser(), PetLoginDomain.class);
		loginUserInfoVo.setAccessToken(token);
		loginUserInfoVo.setRefreshToken(token);
		LoginUserVo loginUserVo = CopyBeanUtils.copyBean(loginUser.getUser(), LoginUserVo.class);
		loginUserInfoVo.setUser(loginUserVo);
		redisService.set(PetConstants.LOGIN_USER_PREFIX + userId,loginUser);
		return loginUserInfoVo;
	}

	@Override
	public PetLoginDomain loginBySms(LoginDto loginDto) {
		return null;
	}

	@Override
	public PetLoginDomain register(RegisterDto registerDto) {
		// 查询是否已注册过
		User user = userService.getByUsername(registerDto.getMobile());
		if (Objects.nonNull(user)) {
			throw new BizException(ResultEnum.REGISTERED_ALREADY);
		}
		// 验证码是否过期
		Object code = redisService.get(PetConstants.SMS_PREFIX + registerDto.getMobile());
		Optional.ofNullable(code).orElseThrow(() -> new BizException(ResultEnum.CODE_DUED));
		String encodePwd = passwordEncoder.encode(registerDto.getPassword());
		registerDto.setPassword(encodePwd);
		User registerUser = CopyBeanUtils.copyBean(registerDto, User.class);
		registerUser.setUsername(registerDto.getMobile()); // 用户名默认为手机号
		userService.save(registerUser);
		PetLoginDomain loginUserInfoVo = CopyBeanUtils.copyBean(registerUser, PetLoginDomain.class);
		return loginUserInfoVo;
	}

	@Override
	public void logout() {
		Long userId = SecurityUtils.getUserId();
		redisService.del(PetConstants.LOGIN_USER_PREFIX + userId);
	}

	@Override
	public String verifyToken(VerifyTokenDto dto) {
		Claims claims;
		try {
			claims = JwtUtil.parseJWT(dto.getToken());
		} catch (Exception e) {
			e.printStackTrace();
			return "token invalid!";
		}
		if (Objects.isNull(claims)) {
			return "token invalid!";
		}
		return dto.getToken();
	}
}
