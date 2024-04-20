package com.moments.claw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moments.claw.domain.base.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.Set;


/**
 * 角色表(Role)表数据库访问层
 *
 * @author chandler
 * @since 2024-04-20 21:12:27
 */
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 根据用户id获取权限集合
	 * @param userId 用户id
	 * @return 权限集合
	 */
	Set<String> selectPermsByUserId(@Param("userId") Long userId);
}

