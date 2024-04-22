package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.vo.IntegralItemVo;
import com.moments.claw.mapper.IntegralItemMapper;
import com.moments.claw.service.ExchangeRecordService;
import com.moments.claw.service.IntegralItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

/**
 * 积分商品表(IntegralItem)表服务实现类
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
@Service("integralItemService")
@RequiredArgsConstructor
public class IntegralItemServiceImpl extends ServiceImpl<IntegralItemMapper, IntegralItem> implements IntegralItemService {

	private final ExchangeRecordService exchangeRecordService;

	@Override
	public List<IntegralItemVo> integralItemList() {
		List<IntegralItem> list = list();
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

