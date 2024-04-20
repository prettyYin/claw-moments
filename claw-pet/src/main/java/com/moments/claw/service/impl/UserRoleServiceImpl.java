package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.UserRole;
import com.moments.claw.mapper.UserRoleMapper;
import com.moments.claw.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户、角色关联表(ClawUserRole)表服务实现类
 *
 * @author chandler
 * @since 2024-04-20 21:12:28
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

