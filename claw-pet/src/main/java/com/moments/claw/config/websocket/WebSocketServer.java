package com.moments.claw.config.websocket;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.moments.claw.domain.base.entity.ChatMessage;
import com.moments.claw.domain.common.constant.GlobalConstants;
import com.moments.claw.domain.entity.Message;
import com.moments.claw.service.ChatMessageService;
import com.moments.claw.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
 
/**
 * WebSocket操作类
 */
@ServerEndpoint("/websocket/{userId}")
@Component
@Slf4j
public class WebSocketServer {

    private static ChatMessageService chatMessageService;
    private static UserService userService;

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private Long userId;

    // session集合,存放对应的session
    private static final ConcurrentHashMap<Long, Session> sessionPool = new ConcurrentHashMap<>();
 
    // concurrent包的线程安全Set,用来存放每个客户端对应的WebSocket对象。
    private static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    // 解决属性注入为null的问题
    @Autowired
    public void init(ChatMessageService chatMessageService, UserService userService) {
        WebSocketServer.chatMessageService = chatMessageService;
        WebSocketServer.userService = userService;
    }

    /**
     * 建立WebSocket连接
     *
     * @param session session对象（非http session）
     * @param userId  用户ID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") Long userId) {
        log.info("WebSocket建立连接中,连接用户ID：{}", userId);
        //先检查这个用户是不是注册用户
        if(null==userService.getById(userId)){
            try {
                this.sendMessage( JSONUtil.toJsonStr(new Message<>(400,"rejected","不存在该用户")),session);
            } catch (IOException e) {
                log.error("用户未注册:{},错误信息:{}", userId, e.getMessage());
            }
            return;
        }

        try {
            Session historySession = sessionPool.get(userId);
            // historySession不为空,说明已经有人登陆账号,应该删除登陆的WebSocket对象
            // todo 将已登录的用户挤下线
            if (historySession != null) {
                webSocketSet.remove(historySession);
                historySession.close();
            }
        } catch (IOException e) {
            log.error("重复登录异常,错误信息:" + e.getMessage(), e);
        }
        // 建立连接
        this.session = session;
        this.userId = userId;
        webSocketSet.add(this);
        sessionPool.put(userId, session);
        // 将缓存未读消息推给用户
        List<ChatMessage> messages = chatMessageService.getUnreadMessages(userId);
        Iterator<ChatMessage> it = messages.iterator();
        while (it.hasNext()) {
            ChatMessage msg = it.next();
            //离线消息接收成功后删除消息
            Boolean isSuccess = sendOfflineMessageByUser(JSON.toJSONString(msg));
            if (isSuccess) {
                log.info("从队列中删除离线消息:{}", msg);
                it.remove();
            }
        }
        log.info("建立连接完成,当前在线人数为：{}", webSocketSet.size());
    }
 
    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
 
    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        sessionPool.remove(this.userId);
        log.info("连接断开,当前在线人数为：{}", webSocketSet.size());
    }
 
    /**
     * 接收客户端消息
     *
     * @param message 接收的消息
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("收到客户端发来的消息：{}", message);
        sendMessageByUser(message);
    }
 
    /**
     * 推送消息到指定用户
     *
     * @param message 发送的消息
     */
    public Boolean sendMessageByUser(String message) {
        ChatMessage msg = JSON.parseObject(message, ChatMessage.class);
        Long acceptUserId = msg.getAcceptUserId();
        log.info("用户ID:{},推送内容:{}", acceptUserId, message);
        Session session = sessionPool.get(acceptUserId);
        try {
            Boolean isSuccess = this.sendMessage(message, session);
            if (!isSuccess) {
                // 用户不在线,存储离线消息
                log.info("用户ID：" + acceptUserId + ",离线，未读消息存入缓存中");
                msg.setReaded(GlobalConstants.UN_READ);
                chatMessageService.setUnreadMessages(acceptUserId, message);
            }
        } catch (IOException e) {
            log.error("推送消息到指定用户发生错误：" + e.getMessage(), e);
            return false;
        }
        // todo 缓存聊天记录，后期做定时任务持久化至数据库
        chatMessageService.cacheMessages(message, msg.getSendUserId(), msg.getAcceptUserId());
        return true;
    }
    /**
     * 发送离线消息
     * @param message 发送的消息
     */
    public Boolean sendOfflineMessageByUser(String message) {
        ChatMessage msg = JSON.parseObject(message, ChatMessage.class);
        log.info("用户ID：{},推送内容：{}",msg.getAcceptUserId(),message);
        try {
            this.sendMessage(JSON.toJSONString(msg), session);
        } catch (IOException e) {
            log.error("推送消息到指定用户发生错误：" + e.getMessage(), e);
            return false;
        }
        return true;
    }
 
    /**
     * 群发消息
     * @param message 发送的消息
     */
    public void sendAllMessage(String message) {
        log.info("发送消息：{}", message);
        for (WebSocketServer webSocket : webSocketSet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("群发消息发生错误：" + e.getMessage(), e);
            }
        }
    }

    /**
     * 发送消息
     * @param message 发送消息
     * @param toSession session对象
     * @return 是否发送成功
     */
    private Boolean sendMessage(String message,Session toSession) throws IOException {
        if (null != toSession && toSession.isOpen()) { //判断session是否正常
            toSession.getBasicRemote().sendText(message);
            return true;
        }
        return false;
    }

}