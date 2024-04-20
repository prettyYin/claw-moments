package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.ActivityArticle;

import java.util.List;


/**
 * (ActivityArticle)表服务接口
 *
 * @author chandler
 * @since 2024-03-24 03:40:49
 */
public interface ActivityArticleService extends IService<ActivityArticle> {

	@SuppressWarnings({"unchecked", "VariableArgumentMethod"})
	default LambdaQueryChainWrapper<ActivityArticle> select(SFunction<ActivityArticle, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	List<ActivityArticle> getActivityArticleInArticleIds(List<Long> articleIds);
}
