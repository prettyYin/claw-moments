package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Pet;

import java.util.List;

/**
* @author admin
* @description 针对表【claw_pet(宠物信息表)】的数据库操作Service
* @createDate 2024-05-06 21:16:10
*/
public interface PetService extends IService<Pet> {

    List<Pet> selectPetList(Pet pet);

    int insertPet(Pet pet);

    int updatePet(Pet pet);

    int deletePetById(List<String> ids);

    Pet selectPetById(String petId);
}