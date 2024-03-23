package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.Participant;
import com.moments.claw.mapper.ParticipantsMapper;
import com.moments.claw.service.ParticipantService;
import org.springframework.stereotype.Service;

/**
 * (ClawParticipants)表服务实现类
 *
 * @author chandler
 * @since 2024-03-23 21:48:54
 */
@Service("participantsService")
public class ParticipantServiceImpl extends ServiceImpl<ParticipantsMapper, Participant> implements ParticipantService {

}

