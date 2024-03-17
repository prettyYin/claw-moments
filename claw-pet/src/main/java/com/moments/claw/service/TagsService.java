package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Tags;

import java.util.List;


/**
 * 标签表(Tags)表服务接口
 *
 * @author chandler
 * @since 2024-03-17 20:50:16
 */
public interface TagsService extends IService<Tags> {

	/**
	 * 根据宠物id获取对应的标签
	 */
	List<Tags> queryTagsByPetId(Long petId);
}
