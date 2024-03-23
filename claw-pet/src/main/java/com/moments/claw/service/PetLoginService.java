package com.moments.claw.service;

import com.moments.claw.domain.dto.LoginDto;
import com.moments.claw.domain.dto.RegisterDto;
import com.moments.claw.domain.entity.PetLoginDomain;

public interface PetLoginService {

	PetLoginDomain loginByPass(LoginDto loginDto);

	PetLoginDomain loginBySms(LoginDto loginDto);

	PetLoginDomain register(RegisterDto registerDto);

	void logout();

	String verifyToken(String token);
}
