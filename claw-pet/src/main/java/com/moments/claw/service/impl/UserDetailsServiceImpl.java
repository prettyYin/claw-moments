package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.common.domain.LoginUser;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.exception.CustomException;
import com.moments.claw.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	UserMapper userMapper;

//	@Resource
//	MenuService menuService;

	@Override
	public UserDetails loadUserByUsername(String mobile) {
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(User::getMobile, mobile);
		User user = userMapper.selectOne(queryWrapper);
		// 用户不存在抛出异常
		Optional.ofNullable(user).orElseThrow(() -> new CustomException(ResultEnum.NEED_LOGIN));
		// 权限封装
//		List<String> permissions = menuService.getPermsByUserId(user.getId());
//		return new LoginUser(user,permissions);
		return new LoginUser(user, null);
	}
}
