package com.moments.claw.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.moments.claw.domain.base.entity.UserRole;


/**
 * 用户、角色关联表(ClawUserRole)表服务接口
 *
 * @author chandler
 * @since 2024-04-20 21:12:28
 */
public interface UserRoleService extends IMppService<UserRole> {

	/**
	 * 根据主键查询已被逻辑删除的数据
	 * @param userId 用户id
	 * @param roleId 角色id
	 * @return 角色关联对象信息
	 */
	UserRole selectByMultiIdIfDelLogic(Long userId, Long roleId);

	/**
	 * 修改逻辑删除
	 * @param userRole 修改对象
	 */
	void updateDelLogicByMultiId(UserRole userRole);
}
