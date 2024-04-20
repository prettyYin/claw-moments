package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Role;
import com.moments.claw.mapper.RoleMapper;
import com.moments.claw.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(Role)表服务实现类
 *
 * @author chandler
 * @since 2024-04-20 21:12:28
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

