package com.moments.claw.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "创建时间")
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@ApiModelProperty(value = "修改时间")
	private Date updatedAt;

	@ApiModelProperty(value = "创建人")
	private String createdBy;

	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "修改人")
	private String updatedBy;

	@ApiModelProperty(value = "删除标志（0存在，-1删除）")
	private Integer delFlag;

	@ApiModelProperty(value = "备注")
	private String remark;
}
