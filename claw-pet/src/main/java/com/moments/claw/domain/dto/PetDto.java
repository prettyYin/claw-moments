package com.moments.claw.domain.dto;

import com.moments.claw.domain.common.domain.PageDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PetDto extends PageDomain {
	@ApiModelProperty(name = "type", value = "类型（1：领养；2：寻宠；3：寻主）")
	private Integer type;
	@ApiModelProperty(name = "cityId", value = "市行政编码")
	private String cityId;
	@ApiModelProperty(name = "cate", value = "种类（1：猫；2：狗；3：其他）")
	private Integer cate;
	@ApiModelProperty(name = "gender", value = "性别（1：雄；2：雌；3：不详）")
	private String gender;
	@ApiModelProperty(name = "age", value = "年龄（如2.5：即2年零6个月）")
	private Object age;
	@ApiModelProperty(name = "status", value = "状态（0异常，1正常，2禁用）")
	private Integer status;
	@ApiModelProperty(name = "vaccine", value = "是否已接种疫苗（0：不详；1：已接种；2：未接种；3：接种中）")
	private Integer vaccine;
	@ApiModelProperty(name = "sterilize", value = "是否已绝育（0：不详；1：已绝育；2：未绝育；3：绝育中）")
	private Integer sterilize;
	@ApiModelProperty(name = "deworm", value = "是否驱虫（0：不详；1：已驱虫；2：未驱虫；3：驱虫中）")
	private Integer deworm;
	@ApiModelProperty(name = "size", value = "体型（0：不详；1：小型；2：中小型；3：中型；4：中大型；5：大型）")
	private Integer size;
	@ApiModelProperty(name = "hair", value = "毛发类型（0：不详；1：短毛；2：长毛）")
	private Integer hair;
}
