package com.jp.authservice.controllers;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.authservice.entities.User;
import com.jp.authservice.services.AuthService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

	@Autowired
	private AuthService service;

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@PostMapping(path = "/login")
	public ResponseEntity login(@RequestBody User user) {
		String token;
		try {
			token = service.login(user);
			if (token != null) {
				return new ResponseEntity(HttpStatus.ACCEPTED).ok(token);
			}
		} catch (AuthenticationException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("User not found.", HttpStatus.BAD_REQUEST);
	}
}
