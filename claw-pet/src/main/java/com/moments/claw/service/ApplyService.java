package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Apply;


/**
 * 领养申请表(Apply)表服务接口
 *
 * @author chandler
 * @since 2024-03-30 16:10:01
 */
public interface ApplyService extends IService<Apply> {

	default LambdaQueryChainWrapper<Apply> select(SFunction<Apply, ?>... columns) {
		return lambdaQuery().select(columns);
	}
}
