package com.moments.claw.biz;

import com.moments.claw.domain.base.entity.ActivityUser;
import com.moments.claw.domain.base.entity.ExchangeRecord;
import com.moments.claw.domain.base.entity.IntegralRecord;
import com.moments.claw.domain.base.entity.User;
import com.moments.claw.domain.base.vo.IntegralDetailVo;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.AuditDto;
import com.moments.claw.domain.vo.UserVo;
import com.moments.claw.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserBiz {

	private final ExchangeRecordService exchangeRecordService;
	private final IntegralRecordService integralRecordService;
	private final ActivityUserService activityUserService;
	private final UserService userService;
	private final FilesService filesService;

	/**
	 * 我的积分明细
	 * @return 积分明细列表
	 */
	public List<IntegralDetailVo> integralDetail() {
		List<IntegralDetailVo> result = new ArrayList<>();

		Long userId = SecurityUtils.getUserId();
		// 积分兑换明细
		List<ExchangeRecord> exchangeRecords = exchangeRecordService.listRecordByUserId(userId);
		// 积分获取明细
		List<IntegralRecord> integralRecords = integralRecordService.listRecordByUserId(userId);

		exchangeRecords.forEach(r -> {
			IntegralDetailVo vo = IntegralDetailVo
					.builder()
					.type("兑换商品")
					.score(-r.getConsumeScore())
					.obtainOrConsumeDate(r.getConsumeDate())
					.build();

			result.add(vo);
		});
		integralRecords.forEach(r -> {
			IntegralDetailVo vo = IntegralDetailVo
					.builder()
					.type(r.getType())
					.score(r.getScore())
					.obtainOrConsumeDate(r.getObtainDate())
					.build();

			result.add(vo);
		});
		return result;
	}

	public List<UserVo> auditList(Long activityId) {
		List<ActivityUser> activityUserList = activityUserService.lambdaQuery().eq(ActivityUser::getActivityId, activityId).eq(ActivityUser::getType, GlobalConstants.AUDIT_TYPE).list();
		if (!activityUserList.isEmpty()) {
			Map<Long, Date> applyDateMap = activityUserList.stream().collect(Collectors.toMap(ActivityUser::getUserId, ActivityUser::getCreatedAt));
			List<Long> userIdList = activityUserList.stream().map(ActivityUser::getUserId).collect(Collectors.toList());
			List<User> userList = userService.lambdaQuery().in(User::getId, userIdList).list();
			List<UserVo> result = CopyBeanUtils.copyBeanList(userList, UserVo.class);
			result.forEach(u -> {
				// 头像
				if (StringUtils.isNotBlank(u.getAvatarId())) {
					String avatar = filesService.getFurl(u.getAvatarId());
					u.setAvatar(avatar);
				}
				// 报名时间
				if (applyDateMap.containsKey(u.getId())) {
					u.setCreatedAt(applyDateMap.get(u.getId()));
				}
			});
			return result;
		}
		return new ArrayList<>();
	}

	public void passApply(AuditDto dto) {
		ActivityUser activityUser = CopyBeanUtils.copyBean(dto, ActivityUser.class);
		ActivityUser existActivityUser = activityUserService.selectByMultiId(activityUser);
		if (existActivityUser != null) {
			existActivityUser.setType(GlobalConstants.PASS_TYPE);
			activityUserService.updateByMultiId(existActivityUser);
		} else {
			activityUser.setType(GlobalConstants.PASS_TYPE);
			activityUserService.saveOrUpdateByMultiId(activityUser);
		}
	}

	public void declineApply(AuditDto dto) {
		ActivityUser activityUser = CopyBeanUtils.copyBean(dto, ActivityUser.class);
		ActivityUser existActivityUser = activityUserService.selectByMultiId(activityUser);
		if (existActivityUser != null) {
			existActivityUser.setType(GlobalConstants.DECLINE_TYPE);
			activityUserService.updateByMultiId(existActivityUser);
		} else {
			activityUser.setType(GlobalConstants.DECLINE_TYPE);
			activityUserService.saveOrUpdateByMultiId(activityUser);
		}
	}
}
