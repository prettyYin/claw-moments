package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Require;
import com.moments.claw.mapper.RequireMapper;
import com.moments.claw.service.RequireService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 领养/救助需求表(Require)表服务实现类
 *
 * @author chandler
 * @since 2024-03-11 22:39:15
 */
@Service("requireService")
public class RequireServiceImpl extends ServiceImpl<RequireMapper, Require> implements RequireService {

	@Override
	public List<Require> queryRequireByPetId(Long petId) {
		List<Require> list = list(new LambdaQueryWrapper<Require>().eq(Require::getPetId,petId));
		return list;
	}
}

