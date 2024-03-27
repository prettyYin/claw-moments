package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.vo.ActivityTypeStatusVo;
import com.moments.claw.mapper.ActivityUserMapper;
import com.moments.claw.service.ActivityUserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 活动、用户关联表(ActivityUser)表服务实现类
 *
 * @author chandler
 * @since 2024-03-24 20:55:38
 */
@Service("activityUserService")
public class ActivityUserServiceImpl extends ServiceImpl<ActivityUserMapper, ActivityUser> implements ActivityUserService {

	@Override
	public ActivityTypeStatusVo getActivityTypeAndThumbStatus(Long activityId, Long userId) {

		ActivityUser activityUser =
				select(ActivityUser::getType, ActivityUser::getThumbStatus)
				.eq(ActivityUser::getActivityId, activityId)
				.eq(ActivityUser::getUserId, userId)
				.one();
		return Objects.isNull(activityUser) ? null : CopyBeanUtils.copyBean(activityUser, ActivityTypeStatusVo.class);
	}
}

