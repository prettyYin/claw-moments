package com.moments.claw.utils;

import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component("perms")
@RequiredArgsConstructor
public class SecurityPermissionUtils {

	private final RoleService roleService;

	/**
	 * 判断是否具有authority权限
	 * @param authority
	 * @return
	 */
	public boolean hasAuthority(String authority) {
		// 判断当前用户
		if (SecurityUtils.isAdmin()) {
			return true;
		}
		// 判断权限
		Long userId = SecurityUtils.getUserId();
		Set<String> permissions = roleService.getPermsByUserId(userId);
		return permissions.contains(authority);
	}
}
