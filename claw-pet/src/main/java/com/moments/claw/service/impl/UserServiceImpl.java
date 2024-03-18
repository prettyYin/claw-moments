package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.mapper.UserMapper;
import com.moments.claw.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (ClawUser)表服务实现类
 *
 * @author chandler
 * @since 2024-03-18 21:33:01
 */
@Service("clawUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

