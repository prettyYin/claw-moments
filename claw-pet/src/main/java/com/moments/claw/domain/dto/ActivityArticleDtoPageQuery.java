package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityArticleDtoPageQuery extends PageQuery {

	@NotNull(message = "活动id不能为空")
	private Long activityId;
}
