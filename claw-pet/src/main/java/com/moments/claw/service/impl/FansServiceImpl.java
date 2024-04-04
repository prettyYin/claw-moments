package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Fans;
import com.moments.claw.mapper.FansMapper;
import com.moments.claw.service.FansService;
import org.springframework.stereotype.Service;

/**
 * 用户粉丝表(Fans)表服务实现类
 *
 * @author chandler
 * @since 2024-04-05 01:32:37
 */
@Service("fansService")
public class FansServiceImpl extends ServiceImpl<FansMapper, Fans> implements FansService {

}

