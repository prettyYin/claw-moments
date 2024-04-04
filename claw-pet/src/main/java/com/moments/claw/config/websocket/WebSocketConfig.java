package com.moments.claw.config.websocket;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import javax.servlet.ServletContext;
 
 
/**
 * WebScoket配置处理器
 */
@Configuration
public class WebSocketConfig implements ServletContextInitializer {
	 /**
     * ServerEndpointExporter 作用
     *
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     *
     * @return
     */
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
 
    //设置websocket发送内容长度
    @Override
    public void onStartup(ServletContext servletContext)  {
        servletContext.setInitParameter("org.apache.tomcat.websocket.textBufferSize","22428800");
    }
}