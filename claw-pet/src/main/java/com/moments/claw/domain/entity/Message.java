package com.moments.claw.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装websocket消息体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message <T>{
    private Integer code;
    private String type;
    private T content;
}