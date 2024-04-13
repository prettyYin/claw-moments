package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 社区文章请求头
 */
@Data
public class CommunityArticleDto extends PageDomain {

	@NotNull(message = "帖子分类不能为空")
	@ApiModelProperty(name = "type", value = "类型（1：领养；2：丢失；3：关注；4：科普；5：救助；6：闲置；7：其他）")
	private Integer type;
	@ApiModelProperty(name = "status", value = "状态（0异常，1正常，2禁用）")
	private Integer status;
}
