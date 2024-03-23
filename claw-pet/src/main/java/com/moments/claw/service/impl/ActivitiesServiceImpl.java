package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Activities;
import com.moments.claw.mapper.ActivitiesMapper;
import com.moments.claw.service.ActivitiesService;
import org.springframework.stereotype.Service;

/**
 * 活动表(Activities)表服务实现类
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Service("activitiesService")
public class ActivitiesServiceImpl extends ServiceImpl<ActivitiesMapper, Activities> implements ActivitiesService {

}

