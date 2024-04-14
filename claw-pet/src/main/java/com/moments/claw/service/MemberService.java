package com.moments.claw.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.moments.claw.domain.base.entity.Member;

import java.util.Optional;


/**
 * (Member)表服务接口
 *
 * @author chandler
 * @since 2024-03-11 22:19:30
 */
public interface MemberService extends IMppService<Member> {

	Member getMemberInfoByUserId(Long userId);
}
