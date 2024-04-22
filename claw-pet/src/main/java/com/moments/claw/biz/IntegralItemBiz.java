package com.moments.claw.biz;

import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.vo.IntegralItemVo;
import com.moments.claw.service.ExchangeRecordService;
import com.moments.claw.service.IntegralItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IntegralItemBiz {

	private final IntegralItemService integralItemService;
	private final ExchangeRecordService exchangeRecordService;

	/**
	 * 获取积分物品列表
	 * @return 所有数据
	 */
	public List<IntegralItemVo> integralItemList() {
		List<IntegralItem> list = integralItemService.list();
		List<IntegralItemVo> result = CopyBeanUtils.copyBeanList(list, IntegralItemVo.class);
		// 赋值是否已兑换过
		result
				.stream()
				.sorted(Comparator.comparing(IntegralItemVo::getGoodsScore).reversed())
				.forEach(i -> {
					ExchangeRecord exchangeRecord = exchangeRecordService.selectByMultiId(ExchangeRecord
							.builder()
							.itemId(i.getId())
							.userId(SecurityUtils.getUserId())
							.build());
					i.setIsExchange(exchangeRecord != null);
				});
		return result;
	}
}
