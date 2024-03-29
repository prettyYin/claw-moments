package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageQuery;
import lombok.Data;

@Data
public class ActivityDtoPageQuery extends PageQuery {
	/**
	 * 活动类型
	 */
	private Integer type;
}
