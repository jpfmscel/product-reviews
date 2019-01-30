package com.adidas.reviewservice.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import com.adidas.reviewservice.exceptions.InvalidCredentialsException;
import com.adidas.reviewservice.exceptions.InvalidTokenException;
import com.adidas.reviewservice.exceptions.MissingTokenException;

@Service
public class AuthService {

	@Value(value = "${auth.basic.user}")
	private String user;

	@Value(value = "${auth.basic.password}")
	private String password;

	public boolean isTokenValid(HttpServletRequest request)
			throws InvalidTokenException, MissingTokenException, InvalidCredentialsException {
		String authorization = request.getHeader("Authorization");

		if (StringUtils.isEmpty(authorization) || !authorization.contains("Basic ")) {
			throw new MissingTokenException("Token is missing.");
		}

		String token = authorization.split("Basic ")[1];
		String credentials = new String(Base64Utils.decodeFromString(token));

		if (credentials.getBytes().length == 0 || !credentials.contains(":")) {
			throw new InvalidTokenException("Invalid Token.");
		}

		String[] credentialsSplit = credentials.split(":");
		if (credentialsSplit.length <= 1) {
			throw new InvalidTokenException("Invalid Token.");
		}
		String username = credentialsSplit[0];
		String password = credentialsSplit[1];

		if (!username.equals(user) || !password.equals(this.password)) {
			throw new InvalidCredentialsException("Invalid credentials.");
		}

		return true;
	}

}
