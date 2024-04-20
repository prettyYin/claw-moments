package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.ActivityArticleDtoPageQuery;
import com.moments.claw.domain.dto.ActivityDtoPageQuery;
import com.moments.claw.domain.dto.MyActivityPageQueryDto;
import com.moments.claw.domain.vo.ActivityReplyVo;
import com.moments.claw.domain.vo.ActivityTypeStatusVo;
import com.moments.claw.domain.vo.ActivityVo;
import com.moments.claw.mapper.ActivityMapper;
import com.moments.claw.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
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

	/*活动进行中*/
	public static final String ACTIVITY_IN_PROGRESS = "1";
	/*活动已结束*/
	public static final String ACTIVITY_ENDED = "2";
	/*我发布的活动*/
	public static final String ACTIVITY_MY_PUBLISH = "3";


	private final ArticleService articleService;
	private final ActivityArticleService activityArticleService;
	private final ActivityUserService activityUserService;
	private final FilesService filesService;

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
		// 赋值报名类型、点赞类型、活动首页图
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
			v.setCoverImageUrl(filesService.getFurl(v.getImageIds().split(",")[0]));
		});
		// 过滤已报名或未审核的列表
		if (Objects.nonNull(pageQuery.getType())) {
			ret = ret.stream().filter(r -> r.getType().equals(pageQuery.getType())).collect(Collectors.toList());
		}
		return PaginationUtil.handPaged(ret, pageQuery.getPageSize(), pageQuery.getPageNum());
	}

	@Override
	public TableDataInfo<?> articleList(ActivityArticleDtoPageQuery pageQuery) {
		@SuppressWarnings("unchecked")
		List<Long> articleIds = activityArticleService
				.select(ActivityArticle::getArticleId)
				.eq(ActivityArticle::getActivityId, pageQuery.getActivityId())
				.list()
				.stream()
				.map(ActivityArticle::getArticleId)
				.collect(Collectors.toList());
		List<ActivityReplyVo> ret = null;
		if (!articleIds.isEmpty()) {
			List<Article> articleList = articleService.list(new LambdaQueryWrapper<Article>().in(Article::getId, articleIds).orderByAsc(Article::getCreatedAt));
			ret = CopyBeanUtils.copyBeanList(articleList, ActivityReplyVo.class);
			// 赋值封面图url和图片url列表
			ret.forEach(r -> {
				if (StringUtils.isNotBlank(r.getImageIds())) {
					String[] imgIds = r.getImageIds().split(",");
					String coverImgId = imgIds[0];
					String coverUrl = filesService.getFurl(coverImgId);
					List<String> furls = filesService.getFurlBatch(imgIds);
					r.setCoverImageUrl(coverUrl);
					r.setImageList(furls);
				}
				if (StringUtils.isNotBlank(r.getVideoId())) {
					String furl = filesService.getFurl(r.getVideoId());
					r.setVideoUrl(furl);
				}
			});
		}
		return PaginationUtil.handPaged(ret, pageQuery.getPageSize(), pageQuery.getPageNum());
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

	@Override
	public Activity getActivityById(Serializable id) {
		Activity activity = getById(id);
		if (activity.getImageIds() != null) {
			String furl = filesService.getFurl(activity.getImageIds().split(",")[0]);
			activity.setCoverImageUrl(furl);
		}
		return activity;
	}

	@Override
	public TableDataInfo<?> myActivityList(MyActivityPageQueryDto dto) {
		Long operator = SecurityUtils.getUserId();
		// 查询我参与的所有活动
		List<Activity> myParticipateActivityList = new ArrayList<>();
		// 我发布的所有文章
		List<Article> myPublishArticleList = articleService.getMyParticipate(operator);
		if (!myPublishArticleList.isEmpty()) {
			List<Long> myPublishArticleIds = myPublishArticleList.stream().map(Article::getId).collect(Collectors.toList());
			// 过滤我发布的文章属于参与活动的文章
			List<ActivityArticle> activityArticleList = activityArticleService.getActivityArticleInArticleIds(myPublishArticleIds);
			if (!activityArticleList.isEmpty()) {
				List<Long> activityIds = activityArticleList.stream().map(ActivityArticle::getActivityId).collect(Collectors.toList());
				// 过滤我参与过发布文章的活动列表
				myParticipateActivityList = getBaseMapper().selectBatchIds(activityIds);
			}
		}

		if (myParticipateActivityList.isEmpty()) {
			return new TableDataInfo<>();
		}

		LocalDateTime now = LocalDateTime.now();// 当前时间
		switch (dto.getType()) {
			// 进行中的
			case ACTIVITY_IN_PROGRESS:
				myParticipateActivityList = myParticipateActivityList.stream().filter(activity -> activity.getStartTime().isBefore(now) && activity.getEndTime().isAfter(now)).collect(Collectors.toList());
				break;
			// 已结束的
			case ACTIVITY_ENDED:
				myParticipateActivityList = myParticipateActivityList.stream().filter(activity -> activity.getStartTime().isBefore(now) && activity.getEndTime().isBefore(now)).collect(Collectors.toList());
				break;
			// 我发布的
			case ACTIVITY_MY_PUBLISH:
				List<Activity> myPublishActivity = getMyPublishActivityList(operator);
				if (!myPublishActivity.isEmpty()) {
					setImageUrlsAndSorted(myPublishActivity);
				}
				return PaginationUtil.handPaged(myPublishActivity, dto.getPageSize(), dto.getPageNum());
			default:
				return new TableDataInfo<>();
		}
		setImageUrlsAndSorted(myParticipateActivityList);
		return PaginationUtil.handPaged(myParticipateActivityList, dto.getPageSize(), dto.getPageNum());
	}

	/**
	 * 设置活动首页缩略图
	 */
	private void setImageUrlsAndSorted(List<Activity> myPublishActivity) {
		myPublishActivity.stream().sorted(Comparator.comparing(Activity::getCreatedAt).reversed()).forEach(activity -> {
			String fileId = activity.getImageIds().split(",")[0];
			String fileUrl = filesService.getFurl(fileId);
			activity.setCoverImageUrl(fileUrl);
		});
	}

	/**
	 * 根据userId获取发布的活动列表
	 * @param userId 用户id
	 * @return 活动列表
	 */
	@Override
	public List<Activity> getMyPublishActivityList(Long userId) {
		return lambdaQuery().eq(Activity::getPublishUserId, userId).list();
	}
}

