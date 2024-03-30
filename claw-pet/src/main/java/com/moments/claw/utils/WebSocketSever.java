package com.moments.claw.utils;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.moments.claw.domain.base.entity.ChatMessage;
import lombok.extern.slf4j.Slf4j;
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
public class WebSocketSever {
 
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private Long userId;

    // session集合,存放对应的session
    private static final ConcurrentHashMap<Long, Session> sessionPool = new ConcurrentHashMap<>();
 
    // concurrent包的线程安全Set,用来存放每个客户端对应的WebSocket对象。
    private static final CopyOnWriteArraySet<WebSocketSever> webSocketSet = new CopyOnWriteArraySet<>();
    // 用于存放离线消息
    private static ConcurrentHashMap<Long, List<ChatMessage>> offlineMessageMap = new ConcurrentHashMap();
    /**
     * 建立WebSocket连接
     *
     * @param session session对象（非http session）
     * @param userId  用户ID
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") Long userId) {
        log.info("WebSocket建立连接中,连接用户ID：{}", userId);
        try {
            Session historySession = sessionPool.get(userId);
            // historySession不为空,说明已经有人登陆账号,应该删除登陆的WebSocket对象
            if (historySession != null) {
                webSocketSet.remove(historySession);
                historySession.close();
            }
        } catch (IOException e) {
            log.error("重复登录异常,错误信息：" + e.getMessage(), e);
        }
        // 建立连接
        this.session = session;
        this.userId = userId;
        webSocketSet.add(this);
        sessionPool.put(userId, session);
        //从离线消息队列里面获取消息
        if (offlineMessageMap.containsKey(userId)) {
            List<ChatMessage> list = offlineMessageMap.get(userId);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Object x = it.next();
                //离线消息接收成功后删除消息
                Boolean bb = sendOfflineMessageByUser(JSON.toJSONString(x));
                if (bb) {
                    System.out.println("从队列中删除离线消息" + x);
                    it.remove();
                }
            }
            offlineMessageMap.remove(userId);
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
    public static Boolean sendMessageByUser(String message) {
        ChatMessage msg = JSON.parseObject(message, ChatMessage.class);
        log.info("用户ID：" + msg.getAcceptUserId() + ",推送内容：" + message);
        Session session = sessionPool.get(msg.getAcceptUserId());
        //判断session是否正常
        if (session == null || !session.isOpen()) {
            log.info("用户ID：" + msg.getAcceptUserId() + ",离线，放入离线消息队列中");
            if (offlineMessageMap.containsKey(msg.getAcceptUserId())) {
                List<ChatMessage> list = offlineMessageMap.get(msg.getAcceptUserId());
                list.add(msg);
                offlineMessageMap.put(msg.getAcceptUserId(), list);
            } else {
                offlineMessageMap.put(msg.getAcceptUserId(), ListUtil.toList(msg));
            }
        }//发送消息
        else {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("推送消息到指定用户发生错误：" + e.getMessage(), e);
                return false;
            }
        }
        return true;
    }
 
    //发送离线消息
    public static Boolean sendOfflineMessageByUser(String message) {
        ChatMessage msg = JSON.parseObject(message, ChatMessage.class);
        log.info("用户ID：" + msg.getAcceptUserId() + ",推送内容：" + message);
        Session session = sessionPool.get(msg.getAcceptUserId());
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("推送消息到指定用户发生错误：" + e.getMessage(), e);
            return false;
        }
        return true;
    }
 
    /**
     * 群发消息
     *
     * @param message 发送的消息
     */
    public static void sendAllMessage(String message) {
        log.info("发送消息：{}", message);
        for (WebSocketSever webSocket : webSocketSet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("群发消息发生错误：" + e.getMessage(), e);
            }
        }
    }
 
}