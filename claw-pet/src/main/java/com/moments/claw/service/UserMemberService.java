package com.moments.claw.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.moments.claw.domain.base.entity.UserMember;


/**
 * 用户/会员关联表(ClawUserMember)表服务接口
 *
 * @author chandler
 * @since 2024-04-22 15:03:36
 */
public interface UserMemberService extends IMppService<UserMember> {

	/**
	 * 查询积分
	 */
	Long integralCount();

	/**
	 * 获取用户的会员信息
	 * @param userId 用户id
	 * @return 会员信息
	 */
	UserMember getMemberInfoByUserId(Long userId);

	/**
	 * 添加用户积分数量
	 * @param userId 用户id
	 * @param integral 积分数
	 */
	void addIntegral(Long userId, Integer integral);
}
