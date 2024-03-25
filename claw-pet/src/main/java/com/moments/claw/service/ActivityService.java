package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Activity;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;

import java.util.List;


/**
 * 活动表(Activity)表服务接口
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
public interface ActivityService extends IService<Activity> {

	TableDataInfo<?> recommendList(PageQuery pageQuery);
}
