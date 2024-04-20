package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Role;
import com.moments.claw.mapper.RoleMapper;
import com.moments.claw.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色表(Role)表服务实现类
 *
 * @author chandler
 * @since 2024-04-20 21:12:28
 */
@Service("roleService")
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

	private final RoleMapper roleMapper;

	@Override
	public Set<String> getPermsByUserId(Long userId) {
		// 超管返回所有权限
		if (userId.equals(1L)) {
			return list().stream().map(Role::getPermissionKey).collect(Collectors.toSet());
		}
		// 返回该用户的权限
		return roleMapper.selectPermsByUserId(userId);
	}
}

