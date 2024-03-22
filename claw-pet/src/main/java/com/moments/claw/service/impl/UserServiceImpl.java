package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.common.constant.PetConstants;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.domain.dto.MobileDto;
import com.moments.claw.mapper.UserMapper;
import com.moments.claw.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * (ClawUser)表服务实现类
 *
 * @author chandler
 * @since 2024-03-18 21:33:01
 */
@Service("clawUserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Resource
	private RedisService redisService;

	@Override
	public User getByMobile(String mobile) {
		User user = getOne(new LambdaQueryWrapper<User>()
				.eq(StringUtils.isNotBlank(mobile), User::getMobile, mobile)
		);
		return user;
	}

	@Override
	public User getByUsername(String username) {
		User user = getOne(new LambdaQueryWrapper<User>()
				.eq(StringUtils.isNotBlank(username), User::getUsername, username)
		);
		return user;
	}

	/**
	 * 获取验证码
	 * @param mobileDto
	 * @return
	 */
	@Override
	public String smsCode(MobileDto mobileDto) {
		// 暂时先从redis中读取
		String code = randomSmsCode();
		redisService.set(PetConstants.SMS_PREFIX + mobileDto.getMobile(), code, 60);
		String registerCode = (String) redisService.get(PetConstants.SMS_PREFIX + mobileDto.getMobile());
		return registerCode;
	}

	//生成4位数字验证码
	private static String randomSmsCode(){
		String str = "0123456789";
		//将字符串转换为一个新的字符数组。
		char[] VerificationCodeArray = str.toCharArray();
		Random random = new Random();
		int count = 0;//计数器
		StringBuilder stringBuilder = new StringBuilder();
		while(true) {
			//随机生成一个随机数
			int index = random.nextInt(VerificationCodeArray.length);
			char c = VerificationCodeArray[index];
			//限制四位不重复数字
			if (stringBuilder.indexOf(c + "") == -1) {
				stringBuilder.append(c);
				//计数器加1
				count++;
			}
			//当count等于4时结束，随机生成四位数的验证码
			if (count == 4) {
				break;
			}
		}
		return stringBuilder.toString();
	}
}

