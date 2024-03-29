package com.moments.claw.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SendAritcleDto {

	@NotNull(message = "文章id不能为空")
	@ApiModelProperty(name = "activityId", value = "文章id")
	private Long activityId;

	@NotNull(message = "文章标题不能为空")
	@Min(1)
	@Max(100)
	@ApiModelProperty(name = "title", value = "文章标题")
	private String title;

	@NotNull(message = "帖子内容不能为空")
	@ApiModelProperty(name = "content", value = "帖子内容")
	private String content;

	@ApiModelProperty(name = "age", value = "宠物年龄")
	private Integer age;

	@ApiModelProperty(name = "cate", value = "宠物种类（1猫，2狗，3其他）")
	private Integer cate;

	@ApiModelProperty(name = "nickname", value = "宠物昵称")
	private String nickname;

	@ApiModelProperty(name = "breed", value = "品种名称")
	private String breed;

	@ApiModelProperty(name = "gender", value = "性别（0不详，1男生，2女生）")
	private Integer gender;

	@ApiModelProperty(name = "images",value = "图片文件id列表")
	private List<String> images;

	@ApiModelProperty(name = "video",value = "视频文件id")
	private String video;

}
