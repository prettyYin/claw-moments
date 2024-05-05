package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Article;
import com.ruoyi.system.mapper.ArticleMapper;
import com.ruoyi.system.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author chandler
 * @since 2024-03-12 09:29:29
 */
@RequiredArgsConstructor
@Service("petService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleMapper articleMapper;

    /**
     * 根据条件分页查询文章列表
     *
     * @param article 文章信息
     * @return 文章信息集合信息
     */
    @Override
    public List<Article> selectList(Article article) {
        return articleMapper.selectArticleList(article);
    }

    @Override
    public void deleteArticle(List<String> ids) {
        articleMapper.deleteArticleById(ids);
    }
}