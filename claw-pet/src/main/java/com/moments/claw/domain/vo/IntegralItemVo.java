package com.moments.claw.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IntegralItemVo {

	@ApiModelProperty(name = "id", value = "主键", hidden = true)
	private Long id;

	@ApiModelProperty(name = "goodsDesc", value = "商品描述")
	private String goodsDesc;

	@ApiModelProperty(name = "goodsUrl", value = "商品链接")
	private String goodsUrl;

	@ApiModelProperty(name = "goodsScore", value = "商品积分")
	private Integer goodsScore;

	@ApiModelProperty(name = "isExchange", value = "是否已兑换")
	private Boolean isExchange;
}
