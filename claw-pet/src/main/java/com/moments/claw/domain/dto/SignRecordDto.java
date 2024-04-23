package com.moments.claw.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SignRecordDto {

	@NotNull(message = "积分不能为空")
	private Integer integral = 0;

}
