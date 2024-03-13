package com.moments.claw.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Advertise;
import com.moments.claw.mapper.AdvertiseMapper;
import com.moments.claw.service.AdvertiseService;
import org.springframework.stereotype.Service;

/**
 * 广告表(Advertise)表服务实现类
 *
 * @author chandler
 * @since 2024-03-11 22:15:52
 */
@Service("advertiseService")
public class AdvertiseServiceImpl extends ServiceImpl<AdvertiseMapper, Advertise> implements AdvertiseService {

}

