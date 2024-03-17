package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Pet;

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
}
