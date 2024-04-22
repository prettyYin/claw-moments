package com.moments.claw.service;

import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.dto.EquipNowDto;


/**
 * 积分兑换商品记录表(ExchangeRecord)表服务接口
 *
 * @author chandler
 * @since 2024-04-23 00:02:59
 */
public interface ExchangeRecordService extends IMppService<ExchangeRecord> {

	String latestJson();

	void equipNow(EquipNowDto dto);

	/**
	 * 商品兑换
	 * @param exchangeRecord 实体对象
	 * @return 新增结果
	 */
	void addExchangeRecord(ExchangeRecord exchangeRecord);
}
