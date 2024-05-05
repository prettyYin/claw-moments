package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moments.claw.domain.base.entity.Article;

import java.util.List;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author chandler
 * @since 2024-03-11 22:30:38
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectArticleList(Article article);
}