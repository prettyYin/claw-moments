package com.moments.claw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moments.claw.domain.base.entity.ChatMessage;
import com.moments.claw.mapper.ChatMessageMapper;
import com.moments.claw.service.ChatMessageService;
import org.springframework.stereotype.Service;

/**
 * (ChatMessage)表服务实现类
 *
 * @author chandler
 * @since 2024-03-30 17:21:15
 */
@Service("chatMessageService")
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {

}

