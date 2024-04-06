package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.moments.claw.domain.base.entity.Fans;
import com.moments.claw.domain.vo.FansVo;

import java.util.List;


/**
 * 用户粉丝表(Fans)表服务接口
 *
 * @author chandler
 * @since 2024-04-05 01:32:37
 */
public interface FansService extends IMppService<Fans> {

	default LambdaQueryChainWrapper<Fans> select(SFunction<Fans, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	/**
	 * 粉丝数
	 * @param userId 当前用户id
	 */
	Integer fansCount(Long userId);

	/**
	 * 粉丝列表
	 */
	List<FansVo> fansList(Long userId);
}
