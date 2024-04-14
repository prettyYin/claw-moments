package com.moments.claw.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.Member;
import com.moments.claw.mapper.MemberMapper;
import com.moments.claw.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * (Member)表服务实现类
 *
 * @author chandler
 * @since 2024-03-11 22:19:30
 */
@Service("memberService")
public class MemberServiceImpl extends MppServiceImpl<MemberMapper, Member> implements MemberService {

	@Override
	public Member getMemberInfoByUserId(Long userId) {
		return lambdaQuery().eq(Member::getUserId, userId).one();
	}
}

