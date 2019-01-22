package com.jp.authservice.configuration;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jp.authservice.exceptions.ConfigurationException;

import io.jsonwebtoken.security.Keys;

@Configuration
public class SystemVariablesProducer {

	@Value("${key.secret}")
	private String secret;

	@Bean
	public Key getSecretKey() throws ConfigurationException {
		if (secret == null) {
			throw new ConfigurationException("Parameter 'key.secret' not provided as VM argument.");
		}
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
}
