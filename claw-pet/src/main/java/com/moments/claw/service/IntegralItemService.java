package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.IntegralItem;
import com.moments.claw.domain.vo.IntegralItemVo;

import java.util.List;


/**
 * 积分商品表(IntegralItem)表服务接口
 *
 * @author chandler
 * @since 2024-04-23 00:02:58
 */
public interface IntegralItemService extends IService<IntegralItem> {

	List<IntegralItemVo> integralItemList();
}
