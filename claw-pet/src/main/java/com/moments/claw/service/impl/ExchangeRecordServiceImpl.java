package com.moments.claw.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.EquipNowDto;
import com.moments.claw.mapper.ExchangeRecordMapper;
import com.moments.claw.service.ExchangeRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 积分兑换商品记录表(ExchangeRecord)表服务实现类
 *
 * @author chandler
 * @since 2024-04-23 00:02:59
 */
@Service("exchangeRecordService")
public class ExchangeRecordServiceImpl extends MppServiceImpl<ExchangeRecordMapper, ExchangeRecord> implements ExchangeRecordService {

	@Override
	public String latestJson() {
		List<ExchangeRecord> list = lambdaQuery()
				.select(ExchangeRecord::getItemJson)
				.eq(ExchangeRecord::getUserId, SecurityUtils.getUserId())
				.eq(ExchangeRecord::getIsPayload, 1)
				.orderByDesc(ExchangeRecord::getCreatedAt)
				.list();
		if (!list.isEmpty()) {
			return list.get(0).getItemJson();
		}
		return null;
	}

	@Transactional
	@Override
	public void equipNow(EquipNowDto dto) {
		// 先把已装备的图下掉
		lambdaUpdate()
				.eq(ExchangeRecord::getUserId, SecurityUtils.getUserId())
				.eq(ExchangeRecord::getIsPayload, 1)
				.set(ExchangeRecord::getIsPayload, 0)
				.update();
		// 再把当前要装备的图改变状态
		lambdaUpdate()
				.eq(ExchangeRecord::getItemId, dto.getItemId())
				.eq(ExchangeRecord::getUserId, SecurityUtils.getUserId())
				.set(ExchangeRecord::getIsPayload, 1)
				.update();
	}

	@Override
	public void addExchangeRecord(ExchangeRecord exchangeRecord) {
		save(exchangeRecord);
		// 扣除相应积分

	}
}

