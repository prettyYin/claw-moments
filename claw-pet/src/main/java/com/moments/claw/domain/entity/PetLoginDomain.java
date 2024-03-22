package com.moments.claw.domain.entity;

import com.moments.claw.domain.vo.LoginUserVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PetLoginDomain {

	private String accessToken;
	private String refreshToken;
	private LoginUserVo member;
}
