package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Tags;
import com.moments.claw.mapper.TagsMapper;
import com.moments.claw.service.TagsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 标签表(Tags)表服务实现类
 *
 * @author chandler
 * @since 2024-03-17 20:50:16
 */
@Service("tagsService")
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

	@Override
	public List<Tags> queryTagsByPetId(Long petId) {
		List<Tags> list = null;
		if (Objects.nonNull(petId)) {
			list = list(new LambdaQueryWrapper<Tags>().eq(Tags::getPetId, petId));
		}
		return list;
	}
}

