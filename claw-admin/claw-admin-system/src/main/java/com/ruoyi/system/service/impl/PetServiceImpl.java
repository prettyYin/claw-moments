package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Pet;
import com.ruoyi.system.mapper.PetMapper;
import com.ruoyi.system.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【claw_pet(宠物信息表)】的数据库操作Service实现
* @createDate 2024-05-06 21:16:10
*/
@Service
@RequiredArgsConstructor
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService {

    private final PetMapper petMapper;

    @Override
    public List<Pet> selectPetList(Pet pet) {
        return petMapper.selectPetList(pet);
    }

    @Override
    public int insertPet(Pet pet) {
        return petMapper.insertPet(pet);
    }

    @Override
    public int updatePet(Pet pet) {
        return petMapper.updatePet(pet);
    }

    @Override
    public int deletePetById(List<String> ids) {
        return petMapper.deletePetByIds(ids);
    }

    @Override
    public Pet selectPetById(String petId) {
        return petMapper.selectPetById(petId);
    }
}