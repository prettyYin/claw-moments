package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Member;
import com.moments.claw.mapper.MemberMapper;
import com.moments.claw.service.MemberService;
import org.springframework.stereotype.Service;

/**
 * (Member)表服务实现类
 *
 * @author chandler
 * @since 2024-03-11 22:19:30
 */
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}

