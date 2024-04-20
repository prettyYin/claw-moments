package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.ActivityArticle;
import com.moments.claw.mapper.ActivityArticleMapper;
import com.moments.claw.service.ActivityArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (ActivityArticle)表服务实现类
 *
 * @author chandler
 * @since 2024-03-24 03:40:49
 */
@Service("activityArticleService")
@RequiredArgsConstructor
public class ActivityArticleServiceImpl extends ServiceImpl<ActivityArticleMapper, ActivityArticle> implements ActivityArticleService {

	@Override
	public List<ActivityArticle> getActivityArticleInArticleIds(List<Long> articleIds) {
		return lambdaQuery().in(ActivityArticle::getArticleId, articleIds).list();
	}
}

