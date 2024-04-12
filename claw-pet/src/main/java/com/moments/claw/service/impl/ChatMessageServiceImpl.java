package com.moments.claw.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moments.claw.common.constant.PetConstants;
import com.moments.claw.domain.base.entity.ChatMessage;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.common.domain.PageQuery;
import com.moments.claw.domain.common.response.TableDataInfo;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.domain.common.utils.PaginationUtil;
import com.moments.claw.domain.common.utils.SecurityUtils;
import com.moments.claw.domain.dto.ChatMessageRecordDtoPageQuery;
import com.moments.claw.domain.vo.ChatMessageVo;
import com.moments.claw.domain.vo.ChatWithUserVo;
import com.moments.claw.mapper.ChatMessageMapper;
import com.moments.claw.service.ChatMessageService;
import com.moments.claw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
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
	private final UserService userService;

	@Override
	public TableDataInfo<?> recordList(ChatMessageRecordDtoPageQuery pageQuery) {
		Long sendUserId = SecurityUtils.getUserId();
		Long acceptUserId = pageQuery.getAcceptUserId();
		// 获取聊天历史列表
		String key = generateShareKey(sendUserId, acceptUserId);
		List<ChatMessageVo> msgList = getChatHistoryList(key);
		// 设置消息接收/发送类型（对于当前用户而言）
		msgList.forEach(m -> {
			if (m.getSendUserId().equals(sendUserId)) {
				m.setTo("out");
			} else {
				m.setTo("in");
			}
		});
		// 分页
		return PaginationUtil.handPaged(msgList, pageQuery.getPageSize(), pageQuery.getPageNum());
	}


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
	public void cacheMessages(String message, Long sendUserId, Long acceptUserId) {
		String shareKey = generateShareKey(sendUserId, acceptUserId);
		redisService.lPush(shareKey, message);
	}

	@Override
	public TableDataInfo<ChatWithUserVo> chatPersonList(PageQuery pageQuery) {
		Long userId = SecurityUtils.getUserId();
		// 模糊搜索取出所有的keys
		Set<String> allFuzzyKeys = redisService.keys(PetConstants.CHAT_MESSAGE_PREFIX + "*");
		// 通过key获取该用户与每个人的聊天记录列表
		List<ChatWithUserVo> result = new ArrayList<>();
		for (String key : allFuzzyKeys) {
			List<ChatMessageVo> chatHistoryList = getChatHistoryList(key);
			// 转化为ChatWithUserVo,供聊天列表回显数据
			if (CollUtil.isNotEmpty(chatHistoryList)) {
				// 缓存中存放发送者id是当前用户和对方用户的记录,过滤出发送人为对方用户的信息
					// 随便取出一条记录来赋值聊天对方的信息,这里取最后一条数据，因为已经按时间升序排序,最后一条即为最后发送的信息
				ChatMessageVo messageVo = chatHistoryList.get(chatHistoryList.size() - 1);
				if (messageVo.getSendUserId().equals(userId) || messageVo.getAcceptUserId().equals(userId)) { // 自己是这条消息的参与者
					Long id = userId.equals(messageVo.getSendUserId()) ? messageVo.getAcceptUserId() : messageVo.getSendUserId();
					String nickname = userService.getById(messageVo.getAcceptUserId()).getNickname();
					int unReadCount = getUnreadMessages(userId).size();
					ChatWithUserVo resultItem = ChatWithUserVo.builder()
							.id(id)
							.nickName(nickname)
							.avatar(messageVo.getAvatar())
							.latestMessage(messageVo.getContent())
							.unReadCount(unReadCount)
							.sendTime(messageVo.getSendTime())
							.build();

					result.add(resultItem);
				}
			}
		}
		return PaginationUtil.handPaged(result, pageQuery.getPageSize(), pageQuery.getPageNum());
	}

	/**
	 * 获取历史聊天记录
	 * @param key 缓存key
	 * @return 历史聊天记录列表
	 */
	private List<ChatMessageVo> getChatHistoryList(String key) {
		// 获取聊天记录
		List<String> oppositeMsgStr = redisService.lRange(key, 0, -1);
		return oppositeMsgStr
				.stream()
				.map(m -> {
					try {
						return new ObjectMapper().readValue(m, ChatMessageVo.class);
					} catch (IOException e) {
						log.error("数据类型转化错误,{}", e);
					}
					return null;
				})
				// 根据消息创建时间排序
				.sorted(Comparator.comparing(chatMessageVo -> chatMessageVo != null ? chatMessageVo.getSendTime() : null))
				.collect(Collectors.toList());
	}

	/**
	 * 生成共享redis key,确保他们的聊天记录存储于缓存中的同一个列表中
	 * @return 共享key
	 */
	private String generateShareKey(Long id1, Long id2) {
		if (id1.compareTo(id2) <= 0) {
			return PetConstants.CHAT_MESSAGE_PREFIX + id1 + "-" + id2;
		} else {
			return PetConstants.CHAT_MESSAGE_PREFIX + id2 + "-" + id1;
		}
	}
}

