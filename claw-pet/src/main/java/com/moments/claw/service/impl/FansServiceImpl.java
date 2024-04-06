package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.Fans;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.vo.FansVo;
import com.moments.claw.mapper.FansMapper;
import com.moments.claw.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户粉丝表(Fans)表服务实现类
 *
 * @author chandler
 * @since 2024-04-05 01:32:37
 */
@Service("fansService")
@RequiredArgsConstructor
public class FansServiceImpl extends MppServiceImpl<FansMapper, Fans> implements FansService {

	private final UserService userService;
	private final FilesService filesService;
	private final FollowService followService;

	@Override
	public List<FansVo> fansList(Long userId) {
		List<Fans> fans = lambdaQuery().eq(Fans::getUserId, userId).list();
		List<FansVo> result = new ArrayList<>();
		fans.forEach(f -> {
			// 赋值粉丝昵称、头像
			User user = userService.getById(f.getFansId());
			String nickname = "";
			String avatar = "";
			if (Objects.nonNull(user)) {
				nickname = user.getNickname();
				avatar = filesService.getFurl(user.getAvatarId());
			}
			Boolean isFollow = followService.isFollow(userId, f.getFansId());
			result.add(
					FansVo
							.builder()
							.fansUserId(f.getFansId())
							.nickName(nickname)
							.avatar(avatar)
							.isFollow(isFollow)
							.build());
		});
		return result;
	}

	@Override
	public Integer fansCount(Long userId) {
		List<Fans> fans = lambdaQuery().eq(Fans::getUserId, userId).list();
		if (CollectionUtil.isEmpty(fans)) {
			return 0;
		}
		return fans.size();
	}
}

