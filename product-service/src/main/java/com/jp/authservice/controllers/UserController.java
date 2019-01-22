package com.jp.authservice.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.authservice.annotations.Secured;
import com.jp.authservice.entities.User;
import com.jp.authservice.exceptions.RestResponseException;
import com.jp.authservice.services.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

	private final HttpStatus ok = HttpStatus.OK;

	@Autowired
	private UserService service;

	@Secured
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/{id}")
	public ResponseEntity findById(@PathVariable @NonNull String id) {
		try {
			return new ResponseEntity<User>(service.findById(id), ok);
		} catch (RestResponseException e) {
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/signup")
	public ResponseEntity<User> signup(@RequestBody @Valid User u) {
		try {
			return new ResponseEntity<User>(service.insert(u), ok);
		} catch (RestResponseException e) {
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@Secured
	@PutMapping
	public ResponseEntity<User> update(@RequestBody @Valid User u) {
		try {
			return new ResponseEntity<User>(service.update(u), ok);
		} catch (RestResponseException e) {
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@Secured
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(service.findAll(), ok);
	}

	@Secured
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable @NonNull String id) {
		try {
			service.delete(id);
			return new ResponseEntity(ok);
		} catch (RestResponseException e) {
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}

	}
}
