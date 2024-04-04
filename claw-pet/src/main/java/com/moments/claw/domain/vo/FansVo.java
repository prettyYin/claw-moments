package com.moments.claw.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FansVo {

	private Long fansUserId;

	private String nickName;

	private String avatar;
}

