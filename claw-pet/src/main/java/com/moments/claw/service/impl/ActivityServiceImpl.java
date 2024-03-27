package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;
import com.moments.claw.domain.vo.ActivityTypeStatusVo;
import com.moments.claw.domain.vo.ActivityVo;
import com.moments.claw.mapper.ActivityMapper;
import com.moments.claw.service.ActivityArticleService;
import com.moments.claw.service.ActivityService;
import com.moments.claw.service.ActivityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 活动表(Activities)表服务实现类
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Service("activityService")
@RequiredArgsConstructor
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

	private final ActivityArticleService activityArticleService;
	private final ActivityUserService activityUserService;

	@Override
	public TableDataInfo<?> recommendList(PageQuery pageQuery) {
		List<Activity> list = list(new LambdaQueryWrapper<Activity>()
				.orderByDesc(Activity::getCreatedAt)
		);
		List<ActivityVo> ret = CopyBeanUtils.copyBeanList(list, ActivityVo.class);
		// 赋值报名类型、点赞类型
		ret.forEach(v -> {
			ActivityTypeStatusVo vo = activityUserService.getActivityTypeAndThumbStatus(v.getId(), SecurityUtils.getUserId());
			// 当vo为null时，返回一个临时对象，type为3，thumbStatus为2
			vo = Optional.ofNullable(vo).orElseGet(
					() -> ActivityTypeStatusVo
							.builder()
							.type(GlobalConstants.UN_ENROLL_TYPE)
							.thumbStatus(GlobalConstants.UN_THUMB_UP_TYPE)
							.build());
			v.setType(vo.getType());
			v.setThumbStatus(vo.getThumbStatus());
		});
		return PaginationUtil.handPaged(ret, pageQuery.getPageSize(), pageQuery.getPageNum());
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

