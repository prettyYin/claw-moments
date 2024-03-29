package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;
import com.moments.claw.domain.dto.ActivityDtoPageQuery;
import com.moments.claw.domain.vo.ActivityTypeStatusVo;
import com.moments.claw.domain.vo.ActivityVo;
import com.moments.claw.mapper.ActivityMapper;
import com.moments.claw.service.ActivityArticleService;
import com.moments.claw.service.ActivityService;
import com.moments.claw.service.ActivityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 活动表(Activities)表服务实现类
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

	private final ActivityArticleService activityArticleService;
	private final ActivityUserService activityUserService;

	@Override
	public TableDataInfo<?> recommendList(ActivityDtoPageQuery pageQuery) {
		// 查询有效活动时间内的活动
		LocalDateTime now = LocalDateTime.now();
		List<Activity> list = list(
				new LambdaQueryWrapper<Activity>()
				.lt(Activity::getStartTime, now)
				.gt(Activity::getEndTime, now)
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
		// 过滤已报名或未审核的列表
		if (Objects.nonNull(pageQuery.getType())) {
			ret = ret.stream().filter(r -> r.getType().equals(pageQuery.getType())).collect(Collectors.toList());
		}
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
		List<Activity> articleList = null;
		if (articleIds.size() > 0) {
			articleList = list(new LambdaQueryWrapper<Activity>().in(Activity::getId, articleIds).orderByAsc(Activity::getCreatedAt));
		}
		return PaginationUtil.handPaged(articleList, pageQuery.getPageSize(), pageQuery.getPageNum());
	}

	@Override
	public void incrThumbUp(Long activityId) {
		Activity activity = getById(activityId);
		activity.setThumbCount(activity.getThumbCount() + 1);
		updateById(activity);
	}

	@Override
	public void decrThumbUp(Long activityId) {
		Activity activity = getById(activityId);
		activity.setThumbCount(activity.getThumbCount() - 1);
		updateById(activity);
	}

	@Override
	public void toggleLike(ActivityUser params) {
		ActivityUser activityUser = activityUserService.getActivityUser(params.getActivityId(), SecurityUtils.getUserId());
		// 如果关联表已经有用户与该活动的记录，则只修改点赞类型；否则插入一条新的关联数据到该表
		if (Objects.nonNull(activityUser)) {
			activityUser.setThumbStatus(params.getThumbStatus());
		} else {
			activityUser = ActivityUser
					.builder()
					.activityId(params.getActivityId())
					.userId(SecurityUtils.getUserId())
					.type(params.getType())
					.thumbStatus(params.getThumbStatus())
					.build();
		}
		activityUserService.saveOrUpdateByMultiId(activityUser);
		// 活动点赞数+1或-1
		if (GlobalConstants.THUMB_UP_TYPE.equals(params.getThumbStatus())) {
			incrThumbUp(params.getActivityId());
		} else {
			decrThumbUp(params.getActivityId());
		}
	}

	@Override
	public void incrViewCount(Long id) {
		Activity activity = getById(id);
		activity.setViewCount(activity.getViewCount() + 1);
		updateById(activity);
	}

	@Override
	public void apply(Long userId, Long activityId) {
		ActivityUser activityUser = activityUserService.getActivityUser(activityId, userId);
		activityUser = Optional.ofNullable(activityUser).orElseGet(
				() -> ActivityUser
						.builder()
						.activityId(activityId)
						.thumbStatus(GlobalConstants.UN_THUMB_UP_TYPE)
						.build()
		);
		activityUser.setType(GlobalConstants.AUDIT_TYPE);
		activityUserService.saveOrUpdateByMultiId(activityUser);
	}
}

