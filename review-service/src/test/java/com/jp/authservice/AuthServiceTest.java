package com.jp.authservice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jp.authservice.entities.User;
import com.jp.authservice.services.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthServiceTest {

	@Autowired
	AuthService service;

	@Test
	public void generateJWT() {
		User user = new User("Joao", "teste@123.com");
		String token = service.getToken(user);
		assertTrue(!token.isEmpty());
		assertTrue(service.isTokenValid(token));
	}

}
