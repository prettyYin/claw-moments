package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageQuery;
import lombok.Data;

@Data
public class ChatMessageRecordDtoPageQuery extends PageQuery {

	private Long acceptUserId;
}
