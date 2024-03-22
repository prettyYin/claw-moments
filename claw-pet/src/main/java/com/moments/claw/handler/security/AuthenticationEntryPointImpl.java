package com.moments.claw.handler.security;

import cn.hutool.json.JSONUtil;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证失败异常处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
		e.printStackTrace(); // 打印测试，方便调试
		R<?> result;
		if (e instanceof BadCredentialsException) {
			result = R.fail(ResultEnum.AUTHENTICATION_ERROR.getCode(),e.getMessage());
		} else if (e instanceof InsufficientAuthenticationException) {
			result = R.fail(ResultEnum.NEED_LOGIN);
		} else {
			result = R.fail(ResultEnum.AUTHENTICATION_ERROR.getCode(), "认证或授权失败");
		}

		WebUtils.renderString(response, result.getCode(), JSONUtil.toJsonStr(result));
	}
}
