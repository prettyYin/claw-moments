package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.jeffreyning.mybatisplus.service.IMppService;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.vo.ActivityTypeStatusVo;


/**
 * 活动、用户关联表(ActivityUser)表服务接口
 *
 * @author chandler
 * @since 2024-03-24 20:55:37
 */
public interface ActivityUserService extends IMppService<ActivityUser> {

	default LambdaQueryChainWrapper<ActivityUser> select(SFunction<ActivityUser, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	/**
	 * 获取报名类型及点赞类型
	 */
	ActivityTypeStatusVo getActivityTypeAndThumbStatus(Long activityId, Long userId);

	/**
	 * 根据activityId和userId获取活动用户关联数据
	 */
	ActivityUser getActivityUser(Long activityId, Long userId);
}
