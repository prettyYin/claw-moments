package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.ruoyi.system.mapper.IntegralItemMapper;
import com.ruoyi.system.service.IntegralItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 积分商品表(IntegralItem)表服务实现类
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
@Service("adminIntegralItemService")
@RequiredArgsConstructor
public class IntegralItemServiceImpl extends ServiceImpl<IntegralItemMapper, IntegralItem> implements IntegralItemService {

    private final IntegralItemMapper integralItemMapper;

    @Override
    public List<IntegralItem> selectList(IntegralItem integralItem) {
        return integralItemMapper.selectIntegralItemList(integralItem);
    }
}