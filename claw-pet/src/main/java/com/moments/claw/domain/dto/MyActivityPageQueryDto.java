package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageQuery;
import lombok.Data;

@Data
public class MyActivityPageQueryDto extends PageQuery {

	/**
	 * 活动类型：1进行中的，2已结束的，3我发布的
	 */
	private String type;
}
