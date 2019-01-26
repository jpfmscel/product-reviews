package com.adidas.productservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.productservice.services.ProductService;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

	private final HttpStatus ok = HttpStatus.OK;

	@Autowired
	private ProductService service;

	// @Secured
	@SuppressWarnings({ "rawtypes" })
	@GetMapping(value = "/{id}")
	public ResponseEntity findById(@PathVariable @NonNull String id) {
		// try {
		return new ResponseEntity<Object>(service.findById(id), ok);
		// } catch (RestResponseException e) {
		// return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		// }
	}

}
