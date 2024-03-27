package com.moments.claw.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityTypeStatusVo {

	@ApiModelProperty(name = "type", value = "报名类型(1已报名，2审核中，3未报名)")
	private Integer type;

	@ApiModelProperty(name = "thumbStatus", value = "点赞类型(1已点赞，2未点赞)")
	private Integer thumbStatus;
}
