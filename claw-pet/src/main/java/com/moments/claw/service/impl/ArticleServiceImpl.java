package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.*;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.dto.ArticleDto;
import com.moments.claw.domain.dto.SendArticleFromActivityDto;
import com.moments.claw.domain.dto.SendOrUpdateArticleFromCommunityDto;
import com.moments.claw.domain.vo.ArticleVo;
import com.moments.claw.domain.vo.CommentVo;
import com.moments.claw.domain.vo.UserCommentVo;
import com.moments.claw.mapper.ArticleMapper;
import com.moments.claw.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author chandler
 * @since 2024-03-12 09:29:29
 */
@RequiredArgsConstructor
@Service("petService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

	private final FilesService filesService;
	private final MemberService memberService;
	private final CommentService commentService;
	private final TagsService tagsService;
	private final RequireService requireService;
	private final ActivityArticleService activityArticleService;
	private final UserService userService;

	/**
	 * 赋值图片url
	 */
	private void setImageUrl(ArticleVo article) {
		if(StringUtils.isNotBlank(article.getImageIds())) {
			List<String> imageIds = Arrays.asList(article.getImageIds().split(","));
			List<Files> files = filesService.listByFileIds(imageIds);
			List<String> imageUrls = files.stream().map(Files::getFileUrl).collect(Collectors.toList());
			article.setImages(imageUrls);
		}
	}

	private void setMember(ArticleVo article) {
		if (Objects.nonNull(article.getMemberId())) {
			article.setMember(memberService.getById(article.getMemberId()));
		}
	}

	private void setComments(ArticleVo article) {
		List<Comment> rootComments = commentService.getRootComments();
		List<CommentVo> ret = CopyBeanUtils.copyBeanList(rootComments, CommentVo.class);
		// 赋值评论用户信息
		ret.forEach(commentVo -> {
			User user = userService.getById(commentVo.getUserId());
			String avatar = null;
			String nickName = null;
			if (StringUtils.isNotBlank(user.getAvatarId())) {
				avatar = filesService.getFurl(user.getAvatarId());
			}
			if (StringUtils.isNotBlank(user.getNickname())) {
				nickName = user.getNickname();
			}
			UserCommentVo userVo = UserCommentVo.builder().avatar(avatar).nickName(nickName).build();
			commentVo.setUser(userVo);
		});
		article.setComments(ret);
	}


	private void setTags(ArticleVo article) {
		List<Tags> tags = tagsService.queryTagsByPetId(article.getId());
		List<String> tagNames = tags.stream().map(Tags::getTagName).collect(Collectors.toList());
		article.setTags(tagNames);
	}


	private void setRequirements(ArticleVo article) {
		List<Require> requires = requireService.queryRequireByPetId(article.getId());
		List<String> requireNames = requires.stream().map(Require::getName).collect(Collectors.toList());
		article.setRequirements(requireNames);
	}

	@Override
	public List<ArticleVo> selectAll() {
		List<Article> list = list();
		List<ArticleVo> articleVos = CopyBeanUtils.copyBeanList(list, ArticleVo.class);
		articleVos.forEach(this::setImageUrl);
		return articleVos;
	}

	@Override
	public ArticleVo viewDetailById(Serializable id) {
		Article article = getById(id);
		ArticleVo articleVo = CopyBeanUtils.copyBean(article, ArticleVo.class);
		setImageUrl(articleVo);
		setMember(articleVo);
		setComments(articleVo);
		setTags(articleVo);
		setRequirements(articleVo);
		return articleVo;
	}

	@Override
	public List<ArticleVo> myPetList(PageQuery pageQuery) {

		return null;
	}

	@Override
	public List<ArticleVo> petList(ArticleDto articleDto) {
		List<Article> list = list(new LambdaQueryWrapper<Article>()
				.eq(Objects.nonNull(articleDto.getType()), Article::getType, articleDto.getType())
				.eq(StringUtils.isNotBlank(articleDto.getCityId()), Article::getCityId, articleDto.getCityId())
				.eq(StringUtils.isNotBlank(articleDto.getCityId()), Article::getCityId, articleDto.getCityId())
				.eq(Objects.nonNull(articleDto.getCate()), Article::getCate, articleDto.getCate())
				.eq(StringUtils.isNotBlank(articleDto.getGender()), Article::getCityId, articleDto.getCityId())
				.eq(Objects.nonNull(articleDto.getAge()), Article::getAge, articleDto.getAge())
				.eq(Objects.nonNull(articleDto.getStatus()), Article::getStatus, articleDto.getStatus())
				.eq(Objects.nonNull(articleDto.getVaccine()), Article::getVaccine, articleDto.getVaccine())
				.eq(Objects.nonNull(articleDto.getSterilize()), Article::getSterilize, articleDto.getSterilize())
				.eq(Objects.nonNull(articleDto.getDeworm()), Article::getDeworm, articleDto.getDeworm())
				.eq(Objects.nonNull(articleDto.getSize()), Article::getSize, articleDto.getSize())
				.eq(Objects.nonNull(articleDto.getHair()), Article::getHair, articleDto.getHair())
		);
		List<ArticleVo> ret = CopyBeanUtils.copyBeanList(list, ArticleVo.class);
		// 赋值首页图
		ret.stream().forEach(r -> {
			if (StringUtils.isNotBlank(r.getImageIds())) {
				r.setCoverImageUrl(filesService.getFurl(r.getImageIds().split(",")[0]));
			}
		});
		return ret;
	}

	@Transactional
	@Override
	public void form(SendArticleFromActivityDto dto) {
		// 保存文章信息
		Article article = Article.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.breed(dto.getBreed())
				.age(dto.getAge())
				.gender(dto.getGender())
				.imageIds(String.join(",", dto.getImages()))
				.videoId(dto.getVideo())
				.build();
		save(article);
		// 保存活动文章关联表
		ActivityArticle activityArticle = ActivityArticle.builder().articleId(article.getId()).activityId(dto.getActivityId()).build();
		activityArticleService.save(activityArticle);
	}

	@Override
	public void communityForm(SendOrUpdateArticleFromCommunityDto dto) {
		Article article = Article.builder()
				.title(dto.getTitle())
				.cate(dto.getCate())
				.content(dto.getContent())
				.imageIds(String.join(",", dto.getImages()))
				.videoId(dto.getVideo())
				.build();
		// 保存文章
		if (Objects.isNull(dto.getArticleId())) {
			save(article);
		} else { // 修改文章
			article.setId(dto.getArticleId());
			updateById(article);
		}
	}
}

