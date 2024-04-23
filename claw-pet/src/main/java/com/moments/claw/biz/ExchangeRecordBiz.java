package com.moments.claw.biz;

import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.moments.claw.domain.base.entity.UserMember;
import com.moments.claw.domain.common.exception.BizException;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.service.ExchangeRecordService;
import com.moments.claw.service.IntegralItemService;
import com.moments.claw.service.UserMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExchangeRecordBiz {

	private final ExchangeRecordService exchangeRecordService;
	private final UserMemberService userMemberService;
	private final IntegralItemService integralItemService;

	/**
	 * 商品兑换
	 * @param exchangeRecord 实体对象
	 */
	@Transactional
	public void addExchangeRecord(ExchangeRecord exchangeRecord) {
		// 扣除相应积分
		IntegralItem integralItem = integralItemService.getById(exchangeRecord.getItemId());// 获取兑换物品信息

		if (integralItem != null) {
			Long userId = SecurityUtils.getUserId();
			UserMember userMember = userMemberService.selectByUserId(userId);
			if (userMember.getIntegral().compareTo(Long.parseLong(integralItem.getGoodsScore().toString())) < 0) {
				throw new BizException("积分不足，快去做任务获取积分吧~");
			}
			userMemberService
					.lambdaUpdate()
					.eq(UserMember::getUserId, userId)
					.set(UserMember::getIntegral, userMember.getIntegral() - integralItem.getGoodsScore())
					.update();
			exchangeRecordService.save(exchangeRecord);
		}
	}
}
