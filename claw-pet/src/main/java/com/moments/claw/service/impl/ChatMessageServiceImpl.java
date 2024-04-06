package com.moments.claw.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moments.claw.common.constant.PetConstants;
import com.moments.claw.domain.base.entity.ChatMessage;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.mapper.ChatMessageMapper;
import com.moments.claw.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (ChatMessage)表服务实现类
 *
 * @author chandler
 * @since 2024-03-30 17:21:15
 */
@Service("chatMessageService")
@RequiredArgsConstructor
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {

	private final RedisService redisService;

	@Override
	public TableDataInfo<?> recordList(Long userId) {
//		List<ChatMessage> list = select(ChatMessage::getAcceptUserId).eq(ChatMessage::getSendUserId, userId).groupBy(ChatMessage::getAcceptUserId).list();
//		if (list.size() == 0) { // 没有和该用户的聊天记录
//			return new TableDataInfo<>();
//		}
//		List<Long> acceptUserIds = list.stream().map(ChatMessage::getAcceptUserId).collect(Collectors.toList());
//		// 根据接收用户id分组出聊天记录
////		List<>
//		acceptUserIds.forEach(id -> {
//			List<ChatMessage> messages = list(
//					new LambdaQueryWrapper<ChatMessage>()
//							.eq(ChatMessage::getSendUserId, userId)
//							.eq(ChatMessage::getAcceptUserId, id)
//			);
//		});
//		redisService.lRange(userId + ":" + )
		return null;
	}


	@Async
	@Override
	public List<ChatMessage> getUnreadMessages(Long userId) {
		List<String> unreadMessages = redisService.lRange(PetConstants.UNREAD_CHAT_MESSAGE_PREFIX + userId, 0, -1);
		// 聊天缓存中设为已读
		List<ChatMessage> chatMessages = unreadMessages
				.stream()
				.map(item -> {
					try {
						return new ObjectMapper().readValue(item, ChatMessage.class);
					} catch (JsonProcessingException e) {
						log.error("聊天格式转化错误:{}",e);
					}
					return null;
				})
				.collect(Collectors.toList());
		for (ChatMessage msg : chatMessages) {
			redisService.lRemove(PetConstants.CHAT_MESSAGE_PREFIX, 0, msg);
			msg.setReaded(GlobalConstants.READED);
			msg.setSoundTIme(LocalDateTime.now());
			msg.setReadedNum(msg.getReadedNum() + 1);
			redisService.lPush(PetConstants.CHAT_MESSAGE_PREFIX, JSON.toJSONString(msg));
		}
		// 删除未读记录
		redisService.del(PetConstants.UNREAD_CHAT_MESSAGE_PREFIX + userId);
		return chatMessages;
	}

	@Async
	@Override
	public void setUnreadMessages(Long acceptUserId, String message) {
		redisService.lPush(PetConstants.UNREAD_CHAT_MESSAGE_PREFIX + acceptUserId, message);
	}

	@Async
	@Override
	public void cacheMessages(String message) {
		redisService.lPush(PetConstants.CHAT_MESSAGE_PREFIX, message);
	}
}

