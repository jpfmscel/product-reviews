package com.jp.authservice.interceptors;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jp.authservice.annotations.Secured;
import com.jp.authservice.services.AuthService;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {

	Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private AuthService authService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof HandlerMethod) {
			if (!shouldVerifyToken((HandlerMethod) handler)) {
				return true;
			}
		}

		String authorization = request.getHeader("Authorization");
		if (StringUtils.isEmpty(authorization) && !authorization.contains("Bearer ")) {
			response.setStatus(401);
			response.getOutputStream().print("Token not provided.");
			response.getOutputStream().flush();
			return false;
		}

		String token = authorization.split("Bearer ")[1];
		logger.info("Authorization header : " + authorization);
		logger.info("Token : " + token);
		try {
			authService.isTokenValid(token);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(401);
			response.getOutputStream().print("Token is invalid.");
			response.getOutputStream().flush();
		}

		return false;
	}

	private boolean shouldVerifyToken(HandlerMethod handler) {
		return Arrays.asList(handler.getMethod().getAnnotations()).parallelStream()
				.anyMatch(an -> an.annotationType().equals(Secured.class));
	}

}
