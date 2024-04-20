package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.vo.ActivityTypeStatusVo;
import com.moments.claw.mapper.ActivityUserMapper;
import com.moments.claw.service.ActivityUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 活动、用户关联表(ActivityUser)表服务实现类
 *
 * @author chandler
 * @since 2024-03-24 20:55:38
 */
@Service
public class ActivityUserServiceImpl extends MppServiceImpl<ActivityUserMapper, ActivityUser> implements ActivityUserService {

	@Override
	public ActivityTypeStatusVo getActivityTypeAndThumbStatus(Long activityId, Long userId) {
		@SuppressWarnings("unchecked")
		ActivityUser activityUser =
				select(ActivityUser::getType, ActivityUser::getThumbStatus)
				.eq(ActivityUser::getActivityId, activityId)
				.eq(ActivityUser::getUserId, userId)
				.one();
		return Objects.isNull(activityUser) ? null : CopyBeanUtils.copyBean(activityUser, ActivityTypeStatusVo.class);
	}

	@Override
	public ActivityUser getActivityUser(Long activityId, Long userId) {
		return getOne(
				new LambdaQueryWrapper<ActivityUser>()
						.eq(ActivityUser::getActivityId, activityId)
						.eq(ActivityUser::getUserId, userId)
		);
	}

	@Override
	public List<ActivityUser> getByUserId(Long userId) {
		return lambdaQuery().eq(ActivityUser::getUserId, userId).list();
	}
}

