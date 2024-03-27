package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.vo.ActivityTypeStatusVo;


/**
 * 活动、用户关联表(ActivityUser)表服务接口
 *
 * @author chandler
 * @since 2024-03-24 20:55:37
 */
public interface ActivityUserService extends IService<ActivityUser> {

	default LambdaQueryChainWrapper<ActivityUser> select(SFunction<ActivityUser, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	/**
	 * 用户是否已报名该活动
	 * @param activityId 活动id
	 * @param userId 用户id
	 * @return 报名类型（1已报名，2审核中，3未报名）
	 */
	ActivityTypeStatusVo getActivityTypeAndThumbStatus(Long activityId, Long userId);
}
