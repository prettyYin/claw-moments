package com.moments.claw.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FriendVo {

	private Long friendUserId;

	private String nickName;

	private String avatar;
}
