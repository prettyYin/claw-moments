package com.moments.claw.domain.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCommentVo {

	private String avatar;

	private String nickName;
}
