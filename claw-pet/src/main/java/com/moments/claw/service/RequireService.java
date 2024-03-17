package com.moments.claw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.Require;

import java.util.List;


/**
 * 领养/救助需求表(Require)表服务接口
 *
 * @author chandler
 * @since 2024-03-11 22:39:15
 */
public interface RequireService extends IService<Require> {

	List<Require> queryRequireByPetId(Long id);
}
