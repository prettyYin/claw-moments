package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.moments.claw.domain.vo.FansVo;
import com.moments.claw.domain.vo.FollowVo;
import com.moments.claw.domain.vo.FriendVo;
import com.moments.claw.service.FansService;
import com.moments.claw.service.FollowService;
import com.moments.claw.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("friendService")
public class FriendServiceImpl implements FriendService {

	private final FollowService followService;
	private final FansService fansService;

	@Override
	public List<FriendVo> friendList(Long userId) {
		List<FollowVo> followList = followService.followList(userId);
		List<FansVo> fansList = fansService.fansList(userId);
		Optional<Map<Long, FansVo>> fansMap = CollectionUtil.isNotEmpty(fansList)
				? Optional.of(fansList.stream().collect(Collectors.toMap(FansVo::getFansUserId, Function.identity())))
				: Optional.empty();
		// 互关 ==》我的关注列表的用户id=等于我的粉丝列表的用户id
		List<FollowVo> friendList = fansMap.map(map -> followList.stream()
				.filter(f -> map.containsKey(f.getFollowUserId()))
				.collect(Collectors.toList()))
				.orElse(Collections.emptyList());
		List<FriendVo> result = new ArrayList<>();
		friendList.forEach(friend -> {
			if (Objects.nonNull(friend)) {
				result.add(
						FriendVo
								.builder()
								.friendUserId(friend.getFollowUserId())
								.nickName(friend.getNickName())
								.avatar(friend.getAvatar())
								.build()
				);
			}
		});
		return result;
	}
}
