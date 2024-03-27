package com.moments.claw.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityVo {

	@ApiModelProperty(name = "id", value = "主键", hidden = true)
	private Long id;

	@ApiModelProperty(name = "title", value = "活动标题")
	private String title;

	@ApiModelProperty(name = "coverImageUrl", value = "活动封面图片URL")
	private String coverImageUrl;

	@ApiModelProperty(name = "viewCount", value = "查看数")
	private String viewCount;

	@ApiModelProperty(name = "capacity", value = "活动最大参与人数(0表示不限制人数)")
	private Integer capacity;

	@ApiModelProperty(name = "type", value = "报名类型(1已报名，2审核中，3未报名)")
	private Integer type;

	@ApiModelProperty(name = "thumbStatus", value = "点赞类型(1已点赞，2未点赞)")
	private Integer thumbStatus;
}
