package com.moments.claw.domain.base.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moments.claw.domain.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("claw_chat_message")
public class ChatMessage extends BaseEntity {

	@TableId(type = IdType.AUTO)
	private Integer id;

	private Long sendUserId;

	private Long acceptUserId;

	private String type;

	private String content;

	private Integer readed;

	private Long readedNum;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime soundTIme;

	@TableField(fill = FieldFill.INSERT)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime sendTime;

	@TableField(exist = false)
	private User user;

}