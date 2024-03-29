package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;

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

	TableDataInfo<?> recommendList(PageQuery pageQuery);

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
}
