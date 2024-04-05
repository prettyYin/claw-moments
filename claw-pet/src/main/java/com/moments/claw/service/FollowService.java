package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Follow;
import com.moments.claw.domain.vo.FollowVo;

import java.util.List;


/**
 * 用户关注表(Follow)表服务接口
 *
 * @author chandler
 * @since 2024-04-05 01:36:59
 */
public interface FollowService extends IService<Follow> {

	default LambdaQueryChainWrapper<Follow> select(SFunction<Follow, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	/**
	 * 关注数
	 * @param userId 当前用户id
	 */
	Integer followCount(Long userId);

	/**
	 * 关注列表
	 */
	List<FollowVo> followList(Long userId);

	/**
	 * 是否已关注
	 * @param fansId 粉丝用户id
	 * @param followingId 被关注者用户id
	 * @return 是否已关注
	 */
	Boolean isFollow(Long fansId, Long followingId);
}
