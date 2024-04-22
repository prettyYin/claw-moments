package com.moments.claw.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.common.constant.MemberConstants;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.base.entity.UserMember;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.mapper.UserMemberMapper;
import com.moments.claw.service.UserMemberService;
import com.moments.claw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户/会员关联表(ClawUserMember)表服务实现类
 *
 * @author chandler
 * @since 2024-04-22 15:03:36
 */
@Service("userMemberService")
@RequiredArgsConstructor
public class UserMemberServiceImpl extends MppServiceImpl<UserMemberMapper, UserMember> implements UserMemberService {

	private final UserService userService;

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

	@Override
	public void addIntegral(Long userId, Integer integral) {
		UserMember userMember = lambdaQuery().eq(UserMember::getUserId, userId).one();
		if (userMember == null) {
			User user = userService.getById(userId);
			save(
					UserMember
							.builder()
							.userId(userId)
							.memberId(MemberConstants.POPULAR_MEMBER)
							.integral(integral.longValue())
							.phoneNumber(user.getMobile())
							.preference(0.00)
							.status(1)
							.build()
			);
		} else {
			userMember.setIntegral(userMember.getIntegral() + integral);
			updateByMultiId(userMember);
		}
	}

	@Override
	public UserMember selectByUserId(Long userId) {
		return lambdaQuery().eq(UserMember::getUserId, userId).one();
	}

}

