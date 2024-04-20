package com.moments.claw.domain.common.utils;

import java.util.Random;

/**
 * 验证码工具类
 */
public class SmsUtils {
	/**
	 * 生成4位数字验证码
	 */
	public static String randomSmsCode(){
		String str = "0123456789";
		//将字符串转换为一个新的字符数组。
		char[] VerificationCodeArray = str.toCharArray();
		Random random = new Random();
		int count = 0;//计数器
		StringBuilder stringBuilder = new StringBuilder();
		do {
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
		} while (count != 4);
		return stringBuilder.toString();
	}
}
