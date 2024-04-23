package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MyArticlePageQueryDto extends PageQuery {

	@ApiModelProperty(name = "type", value = "类型（1：领养；2：丢失；3：关注；4：科普；5：救助；6：闲置；7：其他）")
	private Integer type;

}
