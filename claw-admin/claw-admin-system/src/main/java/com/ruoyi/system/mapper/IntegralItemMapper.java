package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moments.claw.domain.base.entity.IntegralItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 积分商品表(IntegralItem)表数据库访问层
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
public interface IntegralItemMapper extends BaseMapper<IntegralItem> {

    List<IntegralItem> selectIntegralItemList(IntegralItem integralItem);

    IntegralItem selectIntegralById(@Param("id") Long id);

    void insertIntegralItem(IntegralItem integralItem);

    void updateIntegralItem(IntegralItem integralItem);

    void deleteIntegralItem(@Param("ids") List<String> ids);
}