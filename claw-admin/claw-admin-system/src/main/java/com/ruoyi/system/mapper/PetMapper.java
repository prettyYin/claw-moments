package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moments.claw.domain.base.entity.Pet;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
* @author admin
* @description 针对表【claw_pet(宠物信息表)】的数据库操作Mapper
* @createDate 2024-05-06 21:16:10
*/
public interface PetMapper extends BaseMapper<Pet> {

    List<Pet> selectPetList(Pet pet);

    int insertPet(Pet pet);

    int updatePet(Pet pet);

    int deletePetByIds(@Param("ids") List<String> ids);

    Pet selectPetById(@PathVariable("petId") String petId);
}