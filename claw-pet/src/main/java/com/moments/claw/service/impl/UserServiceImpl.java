package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.common.constant.PetConstants;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.domain.common.utils.SmsUtils;
import com.moments.claw.domain.dto.MobileDto;
import com.moments.claw.mapper.UserMapper;
import com.moments.claw.service.FilesService;
import com.moments.claw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * (ClawUser)表服务实现类
 *
 * @author chandler
 * @since 2024-03-18 21:33:01
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	private final RedisService redisService;
	private final FilesService filesService;

	@Override
	public User getByMobile(String mobile) {
		return getOne(
				new LambdaQueryWrapper<User>()
				.eq(StringUtils.isNotBlank(mobile), User::getMobile, mobile)
		);
	}

	@Override
	public User getByUsername(String username) {
		return getOne(
				new LambdaQueryWrapper<User>()
				.eq(StringUtils.isNotBlank(username), User::getUsername, username)
		);
	}

	/**
	 * 获取验证码
	 * @param mobileDto 获取验证码请求体
	 * @return 4位数验证码
	 */
	@Override
	public String smsCode(MobileDto mobileDto) {
		// 暂时先从redis中读取
		String code = SmsUtils.randomSmsCode();
		redisService.set(PetConstants.SMS_PREFIX + mobileDto.getMobile(), code, 60);
		return (String) redisService.get(PetConstants.SMS_PREFIX + mobileDto.getMobile());
	}

	@Override
	public User getUserInfoById(Long id) {
		User user = getById(id);
		if (Objects.isNull(user)) {
			throw new BizException("用户不存在！");
		}
		String avatarId = user.getAvatarId();
		String avatar = filesService.getFurl(avatarId);
		user.setAvatar(avatar);
		return user;
	}

	@Override
	public String getFileUrlByUserId(Long userId) {
		User user = getById(userId);
		return filesService.getFurl(user.getAvatarId());
	}

	@Override
	public String getNicknameByUserId(Long userId) {
		User user = getById(userId);
		String nickname = "";
		if (Objects.nonNull(user)) {
			nickname = user.getNickname();
		}
		return nickname;
	}

	@Override
	public List<User> searchUserLikeNickname(String nickname) {
		List<User> users = lambdaQuery().like(User::getNickname, nickname).list();
		users.forEach(user -> {
			String fileId = user.getAvatarId();
			String avatar = filesService.getFurl(fileId);
			user.setAvatar(avatar);
		});
		return users;
	}


}

