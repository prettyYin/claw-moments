package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.mapper.ActivityMapper;
import com.moments.claw.service.ActivityService;
import org.springframework.stereotype.Service;

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

	@Override
	public TableDataInfo<?> recommendList(PageQuery pageQuery) {
		List<Activity> list = list(new LambdaQueryWrapper<Activity>()
				.orderByDesc(Activity::getCreatedAt)
		);
		List<Activity> collect = list.stream().skip((long) pageQuery.getPageSize() * (pageQuery.getPageNum() - 1)).limit(pageQuery.getPageSize()).collect(Collectors.toList());
		TableDataInfo<Activity> result = new TableDataInfo<>();
		result.setPageSize(pageQuery.getPageSize());
		result.setCurrPage(pageQuery.getPageNum());
		result.setPageNum((list.size() + pageQuery.getPageSize() - 1) / pageQuery.getPageSize());
		result.setTotal(list.size());
		result.setRows(collect);
		return result;
	}
}

