package com.moments.claw.handler.filter;
import cn.hutool.json.JSONUtil;
import com.moments.claw.domain.common.domain.LoginUser;
import com.moments.claw.domain.common.enums.ResultEnum;
import com.moments.claw.domain.common.response.R;
import com.moments.claw.domain.common.service.RedisService;
import com.moments.claw.domain.common.utils.JwtUtil;
import com.moments.claw.domain.common.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	@Resource
	private RedisService<String,LoginUser> redisService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// 1.获取token
		String token = request.getHeader("Authorization");
		// 如果token不存在，放行执行后续的filter
		if (!StringUtils.hasText(token)) {
			filterChain.doFilter(request, response);
			return;
		}
		// 2.解析token，获取userId
		Claims claims;
		try {
			claims = JwtUtil.parseJWT(token);
		} catch (Exception e) {
			// 获取不到token，响应给前端提示重新登录
			R<?> result = R.fail(ResultEnum.NEED_LOGIN);
			WebUtils.renderString(response, JSONUtil.toJsonStr(result));
			return;
		}
		String userId = claims.getSubject();
		// 3.拼接userId，从redis中获取用户信息
		LoginUser loginUser = redisService.get("adminlogin:" + userId);
		if (Objects.isNull(loginUser)) { // loginUser为空说明token过期
			R<?> result = R.fail(ResultEnum.NEED_LOGIN);
			WebUtils.renderString(response, JSONUtil.toJsonStr(result));
			return;
		}
		// 4.存入SecurityContextHolder
		UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(loginUser, null, null);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request,response);
	}
}
