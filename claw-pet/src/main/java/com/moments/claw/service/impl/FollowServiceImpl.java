package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.Follow;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.vo.FollowVo;
import com.moments.claw.mapper.FollowMapper;
import com.moments.claw.service.FilesService;
import com.moments.claw.service.FollowService;
import com.moments.claw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户关注表(Follow)表服务实现类
 *
 * @author chandler
 * @since 2024-04-05 01:36:59
 */
@Service("followService")
@RequiredArgsConstructor
public class FollowServiceImpl extends MppServiceImpl<FollowMapper, Follow> implements FollowService {

	private final UserService userService;
	private final FilesService filesService;

	@Override
	public Boolean isFollow(Long fansId, Long followingId) {
		// 构建关注查询条件
		LambdaQueryWrapper<Follow> followQuery = Wrappers.lambdaQuery(Follow.class).eq(Follow::getUserId, fansId).eq(Follow::getFollowId, followingId);
		// 查询关注记录
		Follow follow = getOne(followQuery);
		return Objects.nonNull(follow);
	}


	@Override
	public List<FollowVo> followList(Long userId) {
		List<Follow> following = lambdaQuery().eq(Follow::getUserId, userId).list();
		List<FollowVo> result = new ArrayList<>();
		following.forEach(f -> {
			// 赋值关注者昵称、头像
			User user = userService.getById(f.getFollowId());
			String nickname = "";
			String avatar = "";
			if (Objects.nonNull(user)) {
				nickname = user.getNickname();
				avatar = filesService.getFurl(user.getAvatarId());
			}
			Boolean isFollow = isFollow(userId, f.getFollowId());
			result.add(
					FollowVo
							.builder()
							.followUserId(f.getFollowId())
							.nickName(nickname)
							.avatar(avatar)
							.isFollow(isFollow)
							.build());
		});
		return result;
	}

	@Override
	public Integer followCount(Long userId) {
		List<Follow> following = lambdaQuery().eq(Follow::getUserId, userId).list();
		if (CollectionUtil.isEmpty(following)) {
			return 0;
		}
		return following.size();
	}

}

