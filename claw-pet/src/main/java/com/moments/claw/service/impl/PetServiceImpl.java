package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Pet;
import com.moments.claw.mapper.PetMapper;
import com.moments.claw.service.PetService;
import org.springframework.stereotype.Service;

/**
 * 宠物表(Pet)表服务实现类
 *
 * @author chandler
 * @since 2024-03-12 09:29:29
 */
@Service("petService")
public class PetServiceImpl extends ServiceImpl<PetMapper, Pet> implements PetService {

}

