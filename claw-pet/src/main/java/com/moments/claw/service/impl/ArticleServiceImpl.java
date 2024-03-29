package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.dto.SendAritcleDto;
import com.moments.claw.mapper.ArticleMapper;
import com.moments.claw.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * (Article)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 19:44:32
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

	@Override
	public void form(SendAritcleDto dto) {
		// 保存文章信息

	}
}

