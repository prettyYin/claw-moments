package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.dto.CommunityArticleDto;
import com.moments.claw.domain.dto.IndexArticleDto;
import com.moments.claw.domain.dto.SendArticleFromActivityDto;
import com.moments.claw.domain.dto.SendOrUpdateArticleFromCommunityDto;
import com.moments.claw.domain.vo.ArticleVo;

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

	ArticleVo viewDetailById(Serializable id);

	List<ArticleVo> myPetList(PageQuery pageQuery);

	List<ArticleVo> petList(IndexArticleDto indexArticleDto);

	void form(SendArticleFromActivityDto dto);

	void communityForm(SendOrUpdateArticleFromCommunityDto dto);

	TableDataInfo<?> communityList(CommunityArticleDto dto);
}
