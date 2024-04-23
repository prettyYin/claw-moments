package com.moments.claw.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuditDto {

	@NotNull
	private Long activityId;

	@NotNull
	private Long userId;
}
