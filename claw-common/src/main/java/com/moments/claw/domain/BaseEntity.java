package com.moments.claw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseEntity {

	@JsonIgnore
	@ApiModelProperty(value = "创建时间")
	private Date createdAt;

	@JsonIgnore
	@ApiModelProperty(value = "修改时间")
	private Date updatedAt;

	@JsonIgnore
	@ApiModelProperty(value = "创建人")
	private String createdBy;

	@JsonIgnore
	@ApiModelProperty(value = "修改人")
	private String updatedBy;

	@JsonIgnore
	@ApiModelProperty(value = "删除标志（0存在，-1删除）")
	private Integer delFlag;

	@JsonIgnore
	@ApiModelProperty(value = "备注")
	private String remark;
}
