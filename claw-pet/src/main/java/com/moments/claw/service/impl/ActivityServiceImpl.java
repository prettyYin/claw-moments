package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;
import com.moments.claw.mapper.ActivityMapper;
import com.moments.claw.service.ActivityArticleService;
import com.moments.claw.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 活动表(Activities)表服务实现类
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

	@Resource
	private ActivityArticleService activityArticleService;

	@Override
	public TableDataInfo<?> recommendList(PageQuery pageQuery) {
		List<Activity> list = list(new LambdaQueryWrapper<Activity>()
				.orderByDesc(Activity::getCreatedAt)
		);
		return PaginationUtil.handPaged(list, pageQuery.getPageSize(), pageQuery.getPageNum());
	}

	@Override
	public TableDataInfo<?> articleList(ActivityArticleDtoPageQuery pageQuery) {
		List<Long> articleIds = activityArticleService
				.select(ActivityArticle::getArticleId)
				.eq(ActivityArticle::getActivityId, pageQuery.getActivityId())
				.list()
				.stream()
				.map(ActivityArticle::getArticleId)
				.collect(Collectors.toList());
		List<Activity> articleList = list(new LambdaQueryWrapper<Activity>().in(articleIds.size() > 0, Activity::getId, articleIds).orderByAsc(Activity::getCreatedAt));
		return PaginationUtil.handPaged(articleList, pageQuery.getPageSize(), pageQuery.getPageNum());
	}
}

