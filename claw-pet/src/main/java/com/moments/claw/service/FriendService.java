package com.moments.claw.service;

import com.moments.claw.domain.vo.FriendVo;

import java.util.List;

public interface FriendService {

	List<FriendVo> friendList(Long userId);

	/**
	 * 关注
	 * @param userId 用户
	 * @param followId 关注用户
	 */
	void follow(Long userId, Long followId);


	/**
	 * 取关
	 * @param userId 粉丝
	 * @param followId 关注用户
	 */
	void unfollow(Long userId, Long followId);
}
