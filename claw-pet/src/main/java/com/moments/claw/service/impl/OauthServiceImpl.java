package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Oauth;
import com.moments.claw.mapper.OauthMapper;
import com.moments.claw.service.OauthService;
import org.springframework.stereotype.Service;

/**
 * (Oauth)表服务实现类
 *
 * @author chandler
 * @since 2024-03-18 21:35:13
 */
@Service("oauthService")
public class OauthServiceImpl extends ServiceImpl<OauthMapper, Oauth> implements OauthService {

}

