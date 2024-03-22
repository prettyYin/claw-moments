package com.moments.claw.service;

import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.dto.LoginDto;

public interface PetLoginService {

	R<?> loginByPass(LoginDto loginDto);

	R<?> loginBySms(LoginDto loginDto);
}
