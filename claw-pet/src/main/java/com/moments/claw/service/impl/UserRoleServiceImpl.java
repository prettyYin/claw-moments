package com.moments.claw.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.UserRole;
import com.moments.claw.mapper.UserRoleMapper;
import com.moments.claw.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户、角色关联表(ClawUserRole)表服务实现类
 *
 * @author chandler
 * @since 2024-04-20 21:12:28
 */
@Service("userRoleService")
@RequiredArgsConstructor
public class UserRoleServiceImpl extends MppServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

	private final UserRoleMapper userRoleMapper;

	@Override
	public UserRole selectByMultiIdIfDelLogic(Long userId, Long roleId) {
		// 必须使用xml方式,框架始终会在末尾拼接查询条件del_flag=#{del_flag}
		return userRoleMapper.selectByMultiIdIfLogicDel(userId, roleId);
	}

	@Override
	public void updateDelLogicByMultiId(UserRole userRole) {
		userRoleMapper.updateDelLogicByMultiId(userRole.getDelFlag(),userRole.getUserId(),userRole.getRoleId());
	}
}

