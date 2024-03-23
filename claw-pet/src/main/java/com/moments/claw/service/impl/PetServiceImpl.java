package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.*;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.dto.PetDto;
import com.moments.claw.mapper.PetMapper;
import com.moments.claw.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 宠物表(Pet)表服务实现类
 *
 * @author chandler
 * @since 2024-03-12 09:29:29
 */
@Service("petService")
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService {

	@Resource
	private FilesService filesService;
	@Resource
	private ArticleService articleService;
	@Resource
	private MemberService memberService;
	@Resource
	private CommentService commentService;
	@Resource
	private TagsService tagsService;
	@Resource
	private RequireService requireService;

	/**
	 * 赋值图片url
	 */
	private void setImageUrl(Pet pet) {
		if(StringUtils.isNotBlank(pet.getImageIds())) {
			List<String> imageIds = Arrays.asList(pet.getImageIds().split(","));
			List<Files> files = filesService.listByFileIds(imageIds);
			List<String> imageUrls = files.stream().map(Files::getFileUrl).collect(Collectors.toList());
			pet.setImages(imageUrls);
		}
	}

	private void setArticle(Pet pet) {
		if (Objects.nonNull(pet.getArticleId())) {
			pet.setArticle(articleService.getById(pet.getArticleId()));
		}
	}

	private void setMember(Pet pet) {
		if (Objects.nonNull(pet.getMemberId())) {
			pet.setMember(memberService.getById(pet.getMemberId()));
		}
	}

	private void setComments(Pet pet) {
		List<Comment> rootComments = commentService.getRootComments();
		List<String> res = rootComments.stream().map(Comment::getContent).collect(Collectors.toList());
		pet.setComments(res);
	}


	private void setTags(Pet pet) {
		List<Tags> tags = tagsService.queryTagsByPetId(pet.getId());
		List<String> tagNames = tags.stream().map(Tags::getTagName).collect(Collectors.toList());
		pet.setTags(tagNames);
	}


	private void setRequirements(Pet pet) {
		List<Require> requires = requireService.queryRequireByPetId(pet.getId());
		List<String> requireNames = requires.stream().map(Require::getName).collect(Collectors.toList());
		pet.setRequirements(requireNames);
	}

	@Override
	public List<Pet> selectAll(Pet pet) {
		List<Pet> list = list();
		list.forEach(this::setImageUrl);
		return list;
	}

	@Override
	public Pet viewDetailById(Serializable id) {
		Pet pet = getById(id);
		setImageUrl(pet);
		setArticle(pet);
		setMember(pet);
		setComments(pet);
		setTags(pet);
		setRequirements(pet);
		return pet;
	}

	@Override
	public List<Pet> myPetList(PageQuery pageQuery) {

		return null;
	}

	@Override
	public List<Pet> petList(PetDto petDto) {
		List<Pet> petList = list(new LambdaQueryWrapper<Pet>()
				.eq(Objects.nonNull(petDto.getType()), Pet::getType, petDto.getType())
				.eq(StringUtils.isNotBlank(petDto.getCityId()), Pet::getCityId, petDto.getCityId())
				.eq(StringUtils.isNotBlank(petDto.getCityId()), Pet::getCityId, petDto.getCityId())
				.eq(Objects.nonNull(petDto.getCate()), Pet::getCate, petDto.getCate())
				.eq(StringUtils.isNotBlank(petDto.getGender()), Pet::getCityId, petDto.getCityId())
				.eq(Objects.nonNull(petDto.getAge()), Pet::getAge, petDto.getAge())
				.eq(Objects.nonNull(petDto.getStatus()), Pet::getStatus, petDto.getStatus())
				.eq(Objects.nonNull(petDto.getVaccine()), Pet::getVaccine, petDto.getVaccine())
				.eq(Objects.nonNull(petDto.getSterilize()), Pet::getSterilize, petDto.getSterilize())
				.eq(Objects.nonNull(petDto.getDeworm()), Pet::getDeworm, petDto.getDeworm())
				.eq(Objects.nonNull(petDto.getSize()), Pet::getSize, petDto.getSize())
				.eq(Objects.nonNull(petDto.getHair()), Pet::getHair, petDto.getHair())
		);
		return petList;
	}
}

