package com.moments.claw.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {

	private Long sendUserId;

	private Long acceptUserId;

	private String content;

	private String nickName;

	private Long unReadCount;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime sendTime;

}
