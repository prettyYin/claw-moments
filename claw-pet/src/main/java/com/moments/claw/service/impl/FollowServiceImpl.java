package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Follow;
import com.moments.claw.mapper.FollowMapper;
import com.moments.claw.service.FollowService;
import org.springframework.stereotype.Service;

/**
 * 用户关注表(Follow)表服务实现类
 *
 * @author chandler
 * @since 2024-04-05 01:36:59
 */
@Service("followService")
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

}

