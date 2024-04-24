package com.moments.claw.mapper;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.moments.claw.domain.base.entity.UserRole;
import org.apache.ibatis.annotations.Param;


/**
 * 用户、角色关联表(ClawUserRole)表数据库访问层
 *
 * @author chandler
 * @since 2024-04-20 21:12:28
 */
public interface UserRoleMapper extends MppBaseMapper<UserRole> {

	UserRole selectByMultiIdIfLogicDel(@Param("userId") Long userId, @Param("roleId") Long roleId);

	void updateDelLogicByMultiId(@Param("delFlag") Integer delFlag, @Param("userId") Long userId, @Param("roleId") Long roleId);
}

