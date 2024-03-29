package com.moments.claw.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

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

	@ApiModelProperty(name = "viewCount", value = "浏览数")
	private Long viewCount;

	@ApiModelProperty(name = "participantCount", value = "活动参与人数")
	private Long participantCount;

	@ApiModelProperty(name = "surplusCount", value = "活动剩余人数")
	private Long surplusCount;

	@ApiModelProperty(name = "capacity", value = "活动最大参与人数(0表示不限制人数)")
	private Long capacity;

	@ApiModelProperty(name = "type", value = "报名类型(1已报名，2审核中，3未报名)")
	private Integer type;

	@ApiModelProperty(name = "thumbStatus", value = "点赞类型(1已点赞，2未点赞)")
	private Integer thumbStatus;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	@ApiModelProperty(name = "startTime", value = "活动开始时间")
	private LocalDateTime startTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	@ApiModelProperty(name = "endTime", value = "活动介绍时间")
	private LocalDateTime endTime;
}
