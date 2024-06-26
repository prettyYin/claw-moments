package com.moments.claw.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SendOrUpdateArticleFromCommunityDto {

	@ApiModelProperty(name = "articleId", value = "文章id")
	private Long articleId;

	@ApiModelProperty(name = "activityId", value = "活动id")
	private Long activityId;

	@NotNull(message = "文章标题不能为空")
	@Min(1)
	@Max(100)
	@ApiModelProperty(name = "title", value = "文章标题")
	private String title;

	@NotNull(message = "帖子内容不能为空")
	@ApiModelProperty(name = "content", value = "帖子内容")
	private String content;

	@ApiModelProperty(name = "type", value = "类型（1：领养；2：丢失；3：关注；4：科普；5：救助；6：闲置；7：其他）")
	private Integer type;

	@ApiModelProperty(name = "imageIds",value = "图片文件id列表(编辑时不为空)")
	private String imageIds;

	@ApiModelProperty(name = "images",value = "图片文件id列表(发布时不为空)")
	private List<String> images;

	@ApiModelProperty(name = "video",value = "视频文件id")
	private String video;
}
