package com.moments.claw.domain.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CommentSendDto {

	@NotNull
	private Long articleId;

	@NotNull
	@Min(1)
	private String content;
}
