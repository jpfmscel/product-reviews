package com.adidas.reviewservice.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.adidas.reviewservice.annotations.Secured;
import com.adidas.reviewservice.services.AuthService;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {

	private static final String API_PREFIX = "/api/";

	Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private AuthService authService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean shouldVerifyMethod = false;
		boolean shouldVerifyURL = shouldVerifyToken(request);

		if (handler instanceof HandlerMethod) {
			shouldVerifyMethod = shouldVerifyToken((HandlerMethod) handler);
			if (!shouldVerifyMethod) {
				return true;
			}
		}

		if (shouldVerifyURL && shouldVerifyMethod) {
			return verifyToken(request, response);
		}

		return true;
	}

	private boolean verifyToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorization = request.getHeader("Authorization");
		if (StringUtils.isEmpty(authorization) || !authorization.contains("Bearer ")) {
			fillResponse(response, 401, "Token not provided.");
			return false;
		}

		String token = authorization.split("Bearer ")[1];
		logger.info("Authorization header : " + authorization);
		logger.info("Token : " + token);
		try {
			// TODO
			authService.isTokenValid(token);
		} catch (Exception e) {
			e.printStackTrace();
			fillResponse(response, 401, "Token is invalid.");
			return false;
		}
		return true;
	}

	private void fillResponse(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		response.getOutputStream().print(message);
		response.getOutputStream().flush();
	}

	private boolean shouldVerifyToken(HttpServletRequest request) {
		return request.getServletPath().contains(API_PREFIX);
	}

	private boolean shouldVerifyToken(HandlerMethod handler) {
		return handler.getBeanType().isAnnotationPresent(Secured.class)
				|| handler.getMethod().isAnnotationPresent(Secured.class);
	}

}
