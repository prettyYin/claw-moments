package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Article;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.dto.ArticleDto;
import com.moments.claw.domain.dto.SendArticleDto;
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

	List<ArticleVo> petList(ArticleDto articleDto);

	void form(SendArticleDto dto);
}
