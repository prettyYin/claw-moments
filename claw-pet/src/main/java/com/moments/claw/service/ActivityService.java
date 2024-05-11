package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;
import com.moments.claw.domain.dto.ActivityDtoPageQuery;
import com.moments.claw.domain.dto.ActivityPublishDto;
import com.moments.claw.domain.dto.MyActivityPageQueryDto;

import java.io.Serializable;
import java.util.List;

/**
 * 活动表(Activity)表服务接口
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
public interface ActivityService extends IService<Activity> {

	default LambdaQueryChainWrapper<Activity> select(SFunction<Activity, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	TableDataInfo<?> activityList(ActivityDtoPageQuery pageQuery);

	TableDataInfo<?> articleList(ActivityArticleDtoPageQuery pageQuery);

	/**
	 * 点赞数+1
	 */
	void incrThumbUp(Long activityId);

	/**
	 * 点赞数-1
	 */
	void decrThumbUp(Long activityId);

	void toggleLike(ActivityUser params);

	void incrViewCount(Long id);

	void apply(Long userId, Long activityId);

	Activity getActivityById(Serializable id);

	/**
	 * 我的活动
	 */
	TableDataInfo<?> myActivityList(MyActivityPageQueryDto dto);

	/**
	 * 根据userId获取发布的活动列表
	 *
	 * @param userId 用户id
	 * @return 活动列表
	 */
	List<Activity> getMyPublishActivityList(Long userId);

	void publish(ActivityPublishDto dto);

	/**
	 * 获取已结束的活动列表
	 * @return 已结束的活动列表
	 */
	List<Activity> getEndedActivityList();
}
