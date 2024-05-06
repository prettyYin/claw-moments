package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Pet;
import com.moments.claw.mapper.PetMapper;
import com.moments.claw.service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author admin
* @description 针对表【claw_pet(宠物信息表)】的数据库操作Service实现
* @createDate 2024-05-06 21:16:10
*/
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService{

    @Override
    public List<Pet> listMyPets(Long userId) {
        return lambdaQuery().eq(Pet::getUserId, userId).list();
    }
}




