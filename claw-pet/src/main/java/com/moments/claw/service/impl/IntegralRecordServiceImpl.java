package com.moments.claw.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.IntegralRecord;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.mapper.IntegralRecordMapper;
import com.moments.claw.service.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.moments.claw.service.IntegralRecordService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 用户签到表(IntegralRecord)表服务实现类
 *
 * @author chandler
 * @since 2024-04-22 15:43:15
 */
@Service("integralRecordService")
@RequiredArgsConstructor
public class IntegralRecordServiceImpl extends MppServiceImpl<IntegralRecordMapper, IntegralRecord> implements IntegralRecordService {

	private final UserMemberService userMemberService;

	@Override
	@Transactional
	public Integer signToday(Integer integral) {
		// 查询今日是否已签到
		LocalDate today = LocalDate.now();
		Long operator = SecurityUtils.getUserId();
		IntegralRecord integralRecordToday = getSignRecordDate(operator, today);
		if (null != integralRecordToday) {
			throw new BizException("今日已签到~");
		}
		IntegralRecord integralRecord = IntegralRecord.builder().userId(operator).obtainDate(today).build();
		save(integralRecord); // 保存签到记录
		userMemberService.addIntegral(operator, integral); // 添加积分数量
		// 查询连续签到天数
		Integer signDays = 0;
		signDays = getConsecutiveSignDays(operator, today, signDays);
		return signDays;
	}

	/**
	 * 查询某个用户的连续签到天数
	 * @param userId   查询用户
	 * @param date     查询开始签到的日期
	 * @param signDays 签到日期
	 * @return 连续签到日期
	 */
	@Override
	public Integer getConsecutiveSignDays(Long userId, LocalDate date, Integer signDays) {
		IntegralRecord integralRecordToday = getSignRecordDate(userId, date);
		// 当天和前一天都没有签到记录则返回该签到天数
		if (integralRecordToday == null) {
			return signDays;
		}
		LocalDate beforeDay = date.minusDays(1); // 前一天
		return getConsecutiveSignDays(userId, beforeDay, ++signDays);
	}

	@Override
	public Boolean isSignedToday() {
		Long userId = SecurityUtils.getUserId();
		LocalDate today = LocalDate.now();
		return getSignRecordDate(userId, today) != null;
	}

	/**
	 * 查询用户某日的签到记录
	 */
	@Override
	public IntegralRecord getSignRecordDate(Long userId, LocalDate date) {
		return lambdaQuery().eq(IntegralRecord::getUserId, userId).eq(IntegralRecord::getObtainDate, date).one();
	}

	@Override
	public List<IntegralRecord> listRecordByUserId(Long userId) {
		return lambdaQuery().eq(IntegralRecord::getUserId, userId).list();
	}

}

