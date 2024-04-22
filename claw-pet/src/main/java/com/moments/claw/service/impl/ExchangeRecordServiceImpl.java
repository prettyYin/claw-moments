package com.moments.claw.service.impl;

import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.mapper.ExchangeRecordMapper;
import com.moments.claw.service.ExchangeRecordService;
import org.springframework.stereotype.Service;

/**
 * 积分兑换商品记录表(ExchangeRecord)表服务实现类
 *
 * @author chandler
 * @since 2024-04-23 00:02:59
 */
@Service("exchangeRecordService")
public class ExchangeRecordServiceImpl extends MppServiceImpl<ExchangeRecordMapper, ExchangeRecord> implements ExchangeRecordService {

}

