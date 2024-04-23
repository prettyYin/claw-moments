package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.*;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.utils.CopyBeanUtils;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.CommunityArticleDto;
import com.moments.claw.domain.dto.IndexArticleDto;
import com.moments.claw.domain.dto.SendArticleFromActivityDto;
import com.moments.claw.domain.dto.SendOrUpdateArticleFromCommunityDto;
import com.moments.claw.domain.vo.*;
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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
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
	private final UserMemberService userMemberService;
	private final CommentService commentService;
	private final TagsService tagsService;
	private final RequireService requireService;
	private final ActivityArticleService activityArticleService;
	private final UserService userService;
	private final FollowService followService;

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

	private void setMember(ArticleVo article,Long userId) {
		UserMember userMember = userMemberService.getMemberInfoByUserId(userId);
		Optional.ofNullable(userMember).ifPresent(
				m -> {
					MemberVo memberVo = CopyBeanUtils.copyBean(userMember, MemberVo.class);
					Long memberId = m.getMemberId();
					Member member = memberService.getById(memberId);
					memberVo.setName(member.getName());
					memberVo.setIcon(member.getIcon());
					memberVo.setLevel(member.getLevel());
					article.setMemberVo(memberVo);
				}
		);
	}

	private void setComments(ArticleVo article) {
		List<Comment> rootComments = commentService.getRootComments(article.getId());
		List<CommentVo> ret = CopyBeanUtils.copyBeanList(rootComments, CommentVo.class);
		// 赋值评论用户信息
		Optional.ofNullable(ret).ifPresent(comments -> comments.forEach(comment -> {
			User user = userService.getById(comment.getUserId());
			String avatar = null;
			String nickName = null;
			if (StringUtils.isNotBlank(user.getAvatarId())) {
				avatar = filesService.getFurl(user.getAvatarId());
			}
			if (StringUtils.isNotBlank(user.getNickname())) {
				nickName = user.getNickname();
			}
			UserCommentVo userVo = UserCommentVo.builder().avatar(avatar).nickName(nickName).build();
			comment.setUser(userVo);
		}));
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

	private void setUser(ArticleVo article,Long userId) {
		User user = userService.getById(userId);
		UserVo userVo = CopyBeanUtils.copyBean(user, UserVo.class);
		// 设置头像
		if (StringUtils.isNotBlank(user.getAvatarId())) {
			String avatar = filesService.getFurl(user.getAvatarId());
			userVo.setAvatar(avatar);
		}
		// 设置对于当前用户来说，是否已关注
		Long operator = SecurityUtils.getUserId();
		userVo.setIsFollow(followService.isFollow(userId, operator));
		article.setUser(userVo);
	}

	@Override
	public List<ArticleVo> selectAll() {
		List<Article> list = list();
		List<ArticleVo> articleVos = CopyBeanUtils.copyBeanList(list, ArticleVo.class);
		articleVos.forEach(this::setImageUrl);
		return articleVos;
	}

	@Override
	public ArticleVo viewArticleDetailById(Serializable id) {
		Long userId = SecurityUtils.getUserId();
		Article article = getById(id);
		ArticleVo articleVo = CopyBeanUtils.copyBean(article, ArticleVo.class);
		setImageUrl(articleVo);
		setMember(articleVo, userId);
		setComments(articleVo);
		setTags(articleVo);
		setRequirements(articleVo);
		setUser(articleVo, userId);
		return articleVo;
	}

	@Override
	public List<ArticleVo> myPetList(PageQuery pageQuery) {

		return null;
	}

	@Override
	public List<ArticleVo> petList(IndexArticleDto indexArticleDto) {
		List<Article> list = list(new LambdaQueryWrapper<Article>()
				.eq(Objects.nonNull(indexArticleDto.getType()), Article::getType, indexArticleDto.getType())
				.eq(StringUtils.isNotBlank(indexArticleDto.getCityId()), Article::getCityId, indexArticleDto.getCityId())
				.eq(StringUtils.isNotBlank(indexArticleDto.getCityId()), Article::getCityId, indexArticleDto.getCityId())
				.eq(Objects.nonNull(indexArticleDto.getCate()), Article::getCate, indexArticleDto.getCate())
				.eq(StringUtils.isNotBlank(indexArticleDto.getGender()), Article::getCityId, indexArticleDto.getCityId())
				.eq(Objects.nonNull(indexArticleDto.getAge()), Article::getAge, indexArticleDto.getAge())
				.eq(Objects.nonNull(indexArticleDto.getStatus()), Article::getStatus, indexArticleDto.getStatus())
				.eq(Objects.nonNull(indexArticleDto.getVaccine()), Article::getVaccine, indexArticleDto.getVaccine())
				.eq(Objects.nonNull(indexArticleDto.getSterilize()), Article::getSterilize, indexArticleDto.getSterilize())
				.eq(Objects.nonNull(indexArticleDto.getDeworm()), Article::getDeworm, indexArticleDto.getDeworm())
				.eq(Objects.nonNull(indexArticleDto.getSize()), Article::getSize, indexArticleDto.getSize())
				.eq(Objects.nonNull(indexArticleDto.getHair()), Article::getHair, indexArticleDto.getHair())
		);
		List<ArticleVo> ret = CopyBeanUtils.copyBeanList(list, ArticleVo.class);
		// 赋值首页图
		ret.forEach(r -> {
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
				.userId(SecurityUtils.getUserId())
				.title(dto.getTitle())
				.type(dto.getType())
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

	@Override
	public TableDataInfo<CommunityArticleVo> communityList(CommunityArticleDto dto) {
		List<Article> article = lambdaQuery()
				.eq(Objects.nonNull(dto.getType()), Article::getType, dto.getType())
				.eq(Objects.nonNull(dto.getStatus()), Article::getStatus, dto.getStatus())
				.list();
		List<CommunityArticleVo> resultVo = CopyBeanUtils.copyBeanList(article, CommunityArticleVo.class);
		TableDataInfo<CommunityArticleVo> result = PaginationUtil.handPaged(resultVo, dto.getPageSize(), dto.getPageNum());
		// 先分页后赋值，可以减少数据库的查询次数
		if (CollUtil.isNotEmpty(result.getRows())) {
			result.getRows().forEach(r -> {
				if (Objects.nonNull(r.getUserId())) {
					User articleSendUser = userService.getById(r.getUserId());
					String nickname = articleSendUser.getNickname();
					String avatar = filesService.getFurl(articleSendUser.getAvatarId());
					r.setAvatar(avatar);
					r.setUserName(nickname);
				}
			});
		}
		return result;
	}

	@Override
	public void toggleLike(Long articleId) {
		Article article = getById(articleId);
		Optional.ofNullable(article).ifPresent(item -> lambdaUpdate().set(Article::getPraise, article.getPraise() + 1).eq(Article::getId, articleId).update());
	}

	@Override
	public void incrView(Long articleId) {
		Article article = getById(articleId);
		Optional.ofNullable(article).ifPresent(item -> lambdaUpdate().set(Article::getView, article.getView() + 1).eq(Article::getId, articleId).update());

	}

	@Override
	public Long share(Long id) {
		Article article = getById(id);
		AtomicReference<Long> shareCount = new AtomicReference<>(0L);
		Optional.ofNullable(article).ifPresent(item -> {
			lambdaUpdate().set(Article::getShare, article.getShare() + 1).eq(Article::getId, id).update();
			shareCount.set(getById(id).getShare());
		});
		return shareCount.get();
	}

	@Override
	public List<Article> getMyParticipate(Long userId) {
		return lambdaQuery().eq(Article::getUserId, userId).list();
	}

}

