package com.moments.claw.service;

import com.moments.claw.domain.vo.FriendVo;

import java.util.List;

public interface FriendService {

	public List<FriendVo> friendList(Long userId);
}
