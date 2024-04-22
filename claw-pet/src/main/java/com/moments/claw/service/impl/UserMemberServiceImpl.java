package com.moments.claw.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.UserMember;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.mapper.UserMemberMapper;
import com.moments.claw.service.UserMemberService;
import org.springframework.stereotype.Service;

/**
 * 用户/会员关联表(ClawUserMember)表服务实现类
 *
 * @author chandler
 * @since 2024-04-22 15:03:36
 */
@Service("userMemberService")
public class UserMemberServiceImpl extends MppServiceImpl<UserMemberMapper, UserMember> implements UserMemberService {

	@Override
	public Long integralCount() {
		return lambdaQuery()
				.select(UserMember::getIntegral)
				.eq(UserMember::getUserId, SecurityUtils.getUserId())
				.one()
				.getIntegral();
	}

	@Override
	public UserMember getMemberInfoByUserId(Long userId) {
		return lambdaQuery().eq(UserMember::getUserId, userId).one();
	}
}

