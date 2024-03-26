package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageQuery;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ActivityArticleDtoPageQuery extends PageQuery {

	@NotBlank(message = "活动id不能为空")
	private Long activityId;
}
