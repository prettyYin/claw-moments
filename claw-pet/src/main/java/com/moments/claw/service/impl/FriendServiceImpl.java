package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.moments.claw.domain.base.entity.Fans;
import com.moments.claw.domain.base.entity.Follow;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.vo.FansVo;
import com.moments.claw.domain.vo.FollowVo;
import com.moments.claw.domain.vo.FriendVo;
import com.moments.claw.service.FansService;
import com.moments.claw.service.FollowService;
import com.moments.claw.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service("friendService")
public class FriendServiceImpl implements FriendService {

	private final FollowService followService;
	private final FansService fansService;

	@Lazy // 解决循环依赖问题，但可能因为死锁而造成内存泄漏问题
	@Autowired
	public FriendServiceImpl(FollowService followService,FansService fansService) {
		this.followService = followService;
		this.fansService = fansService;
	}

	@Override
	public List<FriendVo> friendList(Long userId) {
		List<FollowVo> followList = followService.followList(userId);
		List<FansVo> fansList = fansService.fansList(userId);
		Optional<Map<Long, FansVo>> fansMap = CollectionUtil.isNotEmpty(fansList)
				? Optional.of(fansList.stream().collect(Collectors.toMap(FansVo::getFansUserId, Function.identity())))
				: Optional.empty();
		// 互关 ==》我的关注列表的用户id == 我的粉丝列表的用户id
		List<FollowVo> friendList = fansMap.map(map -> followList.stream()
				.filter(f -> map.containsKey(f.getFollowUserId()))
				.collect(Collectors.toList()))
				.orElse(Collections.emptyList());
		List<FriendVo> result = new ArrayList<>();
		friendList.forEach(friend -> {
			Boolean isFollow = followService.isFollow(userId, friend.getFollowUserId());
			result.add(
					FriendVo
							.builder()
							.friendUserId(friend.getFollowUserId())
							.nickName(friend.getNickName())
							.avatar(friend.getAvatar())
							.isFollow(isFollow)
							.build()
			);
		});
		return result;
	}

	@Transactional
	@Override
	public void follow(Long userId, Long followId) {
		followService.saveOrUpdateByMultiId(
				Follow
						.builder()
						.userId(userId)
						.followId(followId)
						.build());
		fansService.saveOrUpdateByMultiId(
				Fans
						.builder()
						.userId(followId)
						.fansId(userId)
						.build());
	}

	@Transactional
	@Override
	public void unfollow(Long userId, Long followId) {
		LambdaQueryWrapper<Follow> followQuery = Wrappers.lambdaQuery(Follow.class).eq(Follow::getUserId, userId).eq(Follow::getFollowId, followId);
		LambdaQueryWrapper<Fans> fansQuery = Wrappers.lambdaQuery(Fans.class).eq(Fans::getUserId, followId).eq(Fans::getFansId, userId);
		Follow follow = followService.getOne(followQuery);
		Fans fans = fansService.getOne(fansQuery);
		if (Objects.isNull(follow) && Objects.nonNull(fans)) {
			throw new BizException("取关失败,请稍后再试!");
		}
		followService.getBaseMapper().deleteByMultiId(follow);
		fansService.getBaseMapper().deleteByMultiId(fans);
	}
}
