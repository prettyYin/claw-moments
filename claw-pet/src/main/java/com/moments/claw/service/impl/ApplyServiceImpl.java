package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Apply;
import com.moments.claw.mapper.ApplyMapper;
import com.moments.claw.service.ApplyService;
import org.springframework.stereotype.Service;

/**
 * 领养申请表(Apply)表服务实现类
 *
 * @author chandler
 * @since 2024-03-30 16:10:01
 */
@Service("applyService")
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

}

