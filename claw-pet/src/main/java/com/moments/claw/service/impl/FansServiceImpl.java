package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Fans;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.vo.FansVo;
import com.moments.claw.mapper.FansMapper;
import com.moments.claw.service.FansService;
import com.moments.claw.service.FilesService;
import com.moments.claw.service.UserService;
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
public class FansServiceImpl extends ServiceImpl<FansMapper, Fans> implements FansService {

	private final UserService userService;
	private final FilesService filesService;

	@Override
	public List<FansVo> fansList(Long userId) {
		List<Fans> fans = lambdaQuery().eq(Fans::getUserId, userId).list();
		List<FansVo> result = new ArrayList<>();
		fans.forEach(follow -> {
			// 赋值关注者昵称、头像
			User user = userService.getById(userId);
			String nickname = "";
			String avatar = "";
			if (Objects.nonNull(user)) {
				nickname = user.getNickname();
				avatar = filesService.getFurl(user.getAvatarId());
			}
			result.add(
					FansVo
							.builder()
							.fansUserId(follow.getFansId())
							.nickName(nickname)
							.avatar(avatar)
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

