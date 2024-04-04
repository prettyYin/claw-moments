package com.moments.claw.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moments.claw.domain.base.entity.ChatMessage;
import com.moments.claw.domain.common.response.TableDataInfo;

import java.util.List;


/**
 * (ChatMessage)表服务接口
 *
 * @author chandler
 * @since 2024-03-30 17:21:15
 */
public interface ChatMessageService extends IService<ChatMessage> {

	default LambdaQueryChainWrapper<ChatMessage> select(SFunction<ChatMessage, ?>... columns) {
		return lambdaQuery().select(columns);
	}

	TableDataInfo<?> recordList(Long userId);

	/**
	 * 从缓存中获取未读消息
	 * @param userId 未读消息的用户id
	 * @return
	 */
	List<ChatMessage> getUnreadMessages(Long userId);

	/**
	 * 缓存未读消息
	 * @param acceptUserId 离线的用户id
	 * @param message 未读消息
	 */
	void setUnreadMessages(Long acceptUserId, String message);

	/**
	 * 缓存聊天记录
	 * @param message 聊天记录
	 */
	void cacheMessages(String message);
}
