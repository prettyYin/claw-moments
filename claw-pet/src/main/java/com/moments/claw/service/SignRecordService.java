package com.moments.claw.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.moments.claw.domain.base.entity.SignRecord;

import java.time.LocalDate;


/**
 * 用户签到表(SignRecord)表服务接口
 *
 * @author chandler
 * @since 2024-04-22 15:43:15
 */
public interface SignRecordService extends IMppService<SignRecord> {

	/**
	 * 今日签到
	 * @return 连续签到天数
	 */
	Integer signToday(Integer integral);

	/**
	 * 查询某个用户的连续签到天数
	 * @param userId   查询用户
	 * @param date     查询开始签到的日期
	 * @param signDays 签到日期
	 * @return 连续签到日期
	 */
	Integer getConsecutiveSignDays(Long userId, LocalDate date, Integer signDays);

	/**
	 * 今日是否签到
	 * @return 今日签到结果
	 */
	Boolean isSignedToday();

	/**
	 * 查询用户某日的签到记录
	 */
	SignRecord getSignRecordDate(Long userId, LocalDate date);
}
