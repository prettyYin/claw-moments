package com.moments.claw.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ActivityReplyVo {

	@ApiModelProperty(name = "id", value = "主键", hidden = true)
	private Long id;

	@ApiModelProperty(name = "title", value = "标题")
	private String title;

	@ApiModelProperty(name = "content", value = "内容")
	private String content;

	@ApiModelProperty(name = "cate", value = "种类（1：猫；2：狗；3：其他）")
	private Integer cate;

	@ApiModelProperty(name = "breed", value = "品种名称")
	private String breed;

	@ApiModelProperty(name = "gender", value = "性别（1：雄；2：雌；3：不详）")
	private Integer gender;

	@ApiModelProperty(name = "age", value = "年龄（1幼年，2成年，3老年）")
	private Integer age;

	@ApiModelProperty(name = "view", value = "点击次数")
	private Long view;

	@ApiModelProperty(name = "praise", value = "点赞数")
	private Long praise;

	@ApiModelProperty(name = "dislike", value = "倒赞数")
	private Long dislike;

	@ApiModelProperty(name = "comment", value = "评论数")
	private Long comment;

	@JsonIgnore
	@ApiModelProperty(name = "imageIds", value = "照片地址ids")
	private String imageIds;

	@JsonIgnore
	@ApiModelProperty(name = "videoId", value = "视频地址id")
	private String videoId;

	@ApiModelProperty(name = "coverImageUrl", value = "封面图")
	private String coverImageUrl;

	@ApiModelProperty(name = "imageList", value = "图片列表")
	private List<String> imageList;

	@ApiModelProperty(name = "videoUrl", value = "视频地址")
	private String videoUrl;
}
