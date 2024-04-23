package com.moments.claw.domain.base.vo;

import lombok.*;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class IntegralDetailVo {

	/*获取/消耗类型*/
	private String type;

	/*获取/消耗时间*/
	private LocalDate obtainOrConsumeDate;

	/*积分数*/
	private Integer score;
}
