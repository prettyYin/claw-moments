package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.dto.*;
import com.moments.claw.domain.vo.ArticleVo;
import com.moments.claw.domain.vo.CommunityArticleVo;

import java.io.Serializable;
import java.util.List;


/**
 * 宠物表(Pet)表服务接口
 *
 * @author chandler
 * @since 2024-03-12 09:29:29
 */
public interface ArticleService extends IService<Article> {

	List<ArticleVo> selectAll();

	ArticleVo viewArticleDetailById(Serializable id);

	/**
	 * 我发布的帖子
	 */
	TableDataInfo<?> myArticleList(MyArticlePageQueryDto dto);

	List<ArticleVo> indexArticleList(IndexArticleDto indexArticleDto);

	void form(SendArticleFromActivityDto dto);

	void communityForm(SendOrUpdateArticleFromCommunityDto dto);

	TableDataInfo<CommunityArticleVo> communityList(CommunityArticleDto dto);

	void toggleLike(Long articleId);

	void incrView(Long articleId);

	Long share(Long id);

	List<Article> getMyParticipate(Long userId);

	ArticleVo myArticleView(Long articleId);

	/**
	 * 根据文章ids获取文章列表信息
	 * @param articleIds 文章ids
	 * @return 文章列表信息
	 */
	List<Article> selectListByIds(List<Long> articleIds);
}
