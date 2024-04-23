package com.moments.claw.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleDeleteDto {

	@NotNull(message = "文章id不能为空")
	private Long id;
}
