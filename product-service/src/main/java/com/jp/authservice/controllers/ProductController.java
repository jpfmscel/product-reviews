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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.authservice.annotations.Secured;
import com.jp.authservice.entities.Product;
import com.jp.authservice.exceptions.RestResponseException;
import com.jp.authservice.services.ProductService;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

	private final HttpStatus ok = HttpStatus.OK;

	@Autowired
	private ProductService service;

	@Secured
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(value = "/{id}")
	public ResponseEntity findById(@PathVariable @NonNull String id) {
		try {
			return new ResponseEntity<Product>(service.findById(id), ok);
		} catch (RestResponseException e) {
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Secured
	@PutMapping
	public ResponseEntity<Product> update(@RequestBody @Valid Product u) {
		try {
			return new ResponseEntity<Product>(service.update(u), ok);
		} catch (RestResponseException e) {
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@Secured
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		return new ResponseEntity<List<Product>>(service.findAll(), ok);
	}

	@Secured
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
