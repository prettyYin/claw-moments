package com.moments.claw.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EquipNowDto {

	@ApiModelProperty(name = "itemId", value = "商品id", hidden = true)
	@NotNull(message = "商品id不能为空")
	private Long itemId;
}
