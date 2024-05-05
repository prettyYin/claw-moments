package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Article;

import java.util.List;


/**
 * 文章(Article)表服务接口
 *
 * @author chandler
 * @since 2024-03-12 09:29:29
 */
public interface ArticleService extends IService<Article> {

	List<Article> selectList(Article article);

	void deleteArticle(List<String> id);
}
