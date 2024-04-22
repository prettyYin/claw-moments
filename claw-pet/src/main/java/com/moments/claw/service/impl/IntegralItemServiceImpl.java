package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.moments.claw.mapper.IntegralItemMapper;
import com.moments.claw.service.IntegralItemService;
import org.springframework.stereotype.Service;

/**
 * 积分商品表(IntegralItem)表服务实现类
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
@Service("integralItemService")
public class IntegralItemServiceImpl extends ServiceImpl<IntegralItemMapper, IntegralItem> implements IntegralItemService {

}

