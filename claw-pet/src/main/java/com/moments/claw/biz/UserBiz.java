package com.moments.claw.biz;

import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.base.entity.IntegralRecord;
import com.moments.claw.domain.base.vo.IntegralDetailVo;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.service.ExchangeRecordService;
import com.moments.claw.service.IntegralRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBiz {

	private final ExchangeRecordService exchangeRecordService;
	private final IntegralRecordService integralRecordService;

	/**
	 * 我的积分明细
	 * @return 积分明细列表
	 */
	public List<IntegralDetailVo> integralDetail() {
		List<IntegralDetailVo> result = new ArrayList<>();

		Long userId = SecurityUtils.getUserId();
		// 积分兑换明细
		List<ExchangeRecord> exchangeRecords = exchangeRecordService.listRecordByUserId(userId);
		// 积分获取明细
		List<IntegralRecord> integralRecords = integralRecordService.listRecordByUserId(userId);

		exchangeRecords.forEach(r -> {
			IntegralDetailVo vo = IntegralDetailVo
					.builder()
					.type("兑换商品")
					.score(-r.getConsumeScore())
					.obtainOrConsumeDate(r.getConsumeDate())
					.build();

			result.add(vo);
		});
		integralRecords.forEach(r -> {
			IntegralDetailVo vo = IntegralDetailVo
					.builder()
					.type(r.getType())
					.score(r.getScore())
					.obtainOrConsumeDate(r.getObtainDate())
					.build();

			result.add(vo);
		});
		return result;
	}
}
