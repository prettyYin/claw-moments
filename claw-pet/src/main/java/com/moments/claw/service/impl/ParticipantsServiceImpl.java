package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Participants;
import com.moments.claw.mapper.ParticipantsMapper;
import com.moments.claw.service.ParticipantsService;
import org.springframework.stereotype.Service;

/**
 * (ClawParticipants)表服务实现类
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Service("clawParticipantsService")
public class ParticipantsServiceImpl extends ServiceImpl<ParticipantsMapper, Participants> implements ParticipantsService {

}

