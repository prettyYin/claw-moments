package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Pet;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.dto.PetDto;

import java.io.Serializable;
import java.util.List;


/**
 * 宠物表(Pet)表服务接口
 *
 * @author chandler
 * @since 2024-03-12 09:29:29
 */
public interface PetService extends IService<Pet> {

	List<Pet> selectAll(Pet pet);

	Pet viewDetailById(Serializable id);

	List<Pet> myPetList(PageQuery pageQuery);

	List<Pet> petList(PetDto petDto);
}
