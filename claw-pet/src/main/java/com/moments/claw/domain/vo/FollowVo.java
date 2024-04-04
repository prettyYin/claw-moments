package com.moments.claw.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FollowVo {

	private Long followUserId;

	private String nickName;

	private String avatar;
}
