package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Pet;

import java.util.List;

/**
* @author admin
* @description 针对表【claw_pet(宠物信息表)】的数据库操作Service
* @createDate 2024-05-06 21:16:10
*/
public interface PetService extends IService<Pet> {

    List<Pet> listMyPets(Long userId);
}
