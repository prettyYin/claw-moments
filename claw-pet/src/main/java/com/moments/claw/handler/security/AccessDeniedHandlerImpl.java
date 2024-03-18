package com.moments.claw.handler.security;

import cn.hutool.json.JSONUtil;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权失败异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
		e.printStackTrace(); // 打印测试，方便调试
		R<?> result = R.fail(e.getMessage());
		WebUtils.renderString(response, JSONUtil.toJsonStr(result));
	}
}
