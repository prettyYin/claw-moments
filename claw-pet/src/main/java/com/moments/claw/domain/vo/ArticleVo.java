package com.moments.claw.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.moments.claw.domain.base.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ArticleVo {
	@ApiModelProperty(name = "id", value = "主键", hidden = true)
	private Long id;

	@ApiModelProperty(name = "title", value = "标题")
	private String title;

	@ApiModelProperty(name = "content", value = "内容")
	private String content;

	@ApiModelProperty(name = "isTop", value = "是否置顶（0：否；1：是）")
	private Integer isTop;

	@ApiModelProperty(name = "memberId", value = "会员id")
	private Long memberId;

	@ApiModelProperty(name = "nickname", value = "宠物姓名")
	private String nickname;

	@ApiModelProperty(name = "mode")
	private Integer mode;

	@ApiModelProperty(name = "money", value = "价格")
	private Double money;

	@ApiModelProperty(name = "refundCondition", value = "退款条件")
	private String refundCondition;

	@ApiModelProperty(name = "type", value = "类型（1：领养；2：寻宠；3：寻主）")
	private Integer type;

	@ApiModelProperty(name = "cate", value = "种类（1：猫；2：狗；3：其他）")
	private Integer cate;

	@ApiModelProperty(name = "breed", value = "品种名称")
	private String breed;

	@ApiModelProperty(name = "gender", value = "性别（1：雄；2：雌；3：不详）")
	private Integer gender;

	@ApiModelProperty(name = "age", value = "年龄（1幼年，2成年，3老年）")
	private Integer age;

	@ApiModelProperty(name = "weight", value = "年龄（1幼年，2成年，3老年）")
	private Double weight;

	@ApiModelProperty(name = "vaccine", value = "是否已接种疫苗（0：不详；1：已接种；2：未接种；3：接种中）")
	private Integer vaccine;

	@ApiModelProperty(name = "vaccineName", value = "接种疫苗名称")
	private String vaccineName;

	@ApiModelProperty(name = "sterilize", value = "是否已绝育（0：不详；1：已绝育；2：未绝育；3：绝育中）")
	private Integer sterilize;

	@ApiModelProperty(name = "sterilizeName", value = "绝育名称")
	private String sterilizeName;

	@ApiModelProperty(name = "deworm", value = "是否驱虫（0：不详；1：已驱虫；2：未驱虫；3：驱虫中）")
	private Integer deworm;

	@ApiModelProperty(name = "dewormName", value = "驱虫")
	private String dewormName;

	@ApiModelProperty(name = "source", value = "来源（0：不详；1：个人救助；2：领养；3：家养）")
	private Integer source;

	@ApiModelProperty(name = "sourceName", value = "来源名称")
	private String sourceName;

	@ApiModelProperty(name = "size", value = "体型（0：不详；1：小型；2：中小型；3：中型；4：中大型；5：大型）")
	private Integer size;

	@ApiModelProperty(name = "sizeName", value = "体型名称")
	private String sizeName;

	@ApiModelProperty(name = "hair", value = "毛发类型（0：不详；1：短毛；2：长毛）")
	private Integer hair;

	@ApiModelProperty(name = "hairName", value = "毛发类型名称")
	private String hairName;

	@ApiModelProperty(name = "otherRequirement", value = "其他需求")
	private String otherRequirement;

	@ApiModelProperty(name = "imageIds", value = "照片地址ids")
	private String imageIds;

	@ApiModelProperty(name = "videoId", value = "视频地址id")
	private String videoId;

	@ApiModelProperty(name = "provinceId", value = "省行政编码")
	private String provinceId;

	@ApiModelProperty(name = "cityId", value = "市行政编码")
	private String cityId;

	@ApiModelProperty(name = "areaId", value = "地区行政编码")
	private String areaId;

	@ApiModelProperty(name = "province", value = "省名称")
	private String province;

	@ApiModelProperty(name = "city", value = "市名称")
	private String city;

	@ApiModelProperty(name = "area", value = "地区名称")
	private String area;

	@ApiModelProperty(name = "address", value = "详细地址")
	private String address;

	@ApiModelProperty(name = "lng", value = "经度")
	private BigDecimal lng;

	@ApiModelProperty(name = "lat", value = "维度")
	private BigDecimal lat;

	@ApiModelProperty(name = "location", value = "地点描述")
	private String location;

	@ApiModelProperty(name = "view", value = "点击次数")
	private Long view;

	@ApiModelProperty(name = "praise", value = "点赞数")
	private Long praise;

	@ApiModelProperty(name = "dislike", value = "倒赞数")
	private Long dislike;

	@ApiModelProperty(name = "comment", value = "评论数")
	private Long comment;

	@ApiModelProperty(name = "share", value = "分享数")
	private Long share;

	@ApiModelProperty(name = "status", value = "状态（0异常，1正常，2禁用）")
	private Integer status;

	@TableField(exist = false)
	@ApiModelProperty(name = "images", value = "照片地址")
	private List<String> images;

	@TableField(exist = false)
	@ApiModelProperty(name = "comments", value = "评论")
	private List<CommentVo> comments;

	@TableField(exist = false)
	@ApiModelProperty(name = "tags", value = "标签")
	private List<String> tags;

	@TableField(exist = false)
	@ApiModelProperty(name = "requirements", value = "要求")
	private List<String> requirements;

	@TableField(exist = false)
	@ApiModelProperty(name = "member", value = "会员信息")
	private Member member;

	@TableField(exist = false)
	@ApiModelProperty(name = "coverImageUrl", value = "首页图url")
	private String coverImageUrl;
}
