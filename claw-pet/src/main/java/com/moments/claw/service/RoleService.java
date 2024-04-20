package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Role;
import java.util.Set;


/**
 * 角色表(Role)表服务接口
 *
 * @author chandler
 * @since 2024-04-20 21:12:28
 */
public interface RoleService extends IService<Role> {

	/**
	 * 返回用户的所有权限
	 * @param userId 用户id
	 * @return 权限集合
	 */
	Set<String> getPermsByUserId(Long userId);
}
