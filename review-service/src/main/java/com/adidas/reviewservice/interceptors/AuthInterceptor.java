package com.adidas.reviewservice.interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.adidas.reviewservice.annotations.Secured;
import com.adidas.reviewservice.services.AuthService;
import com.adidas.reviewservice.util.GenericResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {

	private static final String API_PREFIX = "/api/";

	Logger logger = Logger.getLogger(AuthInterceptor.class);

	@Autowired
	private ObjectMapper objectMapper;

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
			return isTokenValid(request, response);
		}

		return true;
	}

	private boolean isTokenValid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			return authService.isTokenValid(request);
		} catch (Exception e) {
			fillResponse(response, 401, e.getMessage());
			return false;
		}
	}

	private void fillResponse(HttpServletResponse response, int status, String message) throws IOException {
		response.setStatus(status);
		response.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		response.getOutputStream()
				.print(objectMapper.writeValueAsString(GenericResponseUtils.buildGenericResponseError(message)));
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
