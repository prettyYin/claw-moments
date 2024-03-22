package com.moments.claw.service.impl;

import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.common.domain.LoginUser;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	UserService userService;

//	@Resource
//	MenuService menuService;

	@Override
	public UserDetails loadUserByUsername(String mobile) {
		User user = userService.getByMobile(mobile);
		// 用户不存在抛出异常
		Optional.ofNullable(user).orElseThrow(() -> new BizException(ResultEnum.NOT_REGISTERED));
		// 权限封装
//		List<String> permissions = menuService.getPermsByUserId(user.getId());
//		return new LoginUser(user,permissions);
		return new LoginUser(user, null);
	}
}
