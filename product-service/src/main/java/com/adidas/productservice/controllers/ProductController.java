package com.adidas.productservice.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.productservice.services.ProductService;
import com.adidas.productservice.services.ReviewService;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

	private final HttpStatus ok = HttpStatus.OK;

	@Autowired
	private ProductService service;

	@Autowired
	private ReviewService reviewService;

	// @Secured
	@SuppressWarnings({ "rawtypes" })
	@GetMapping(value = "/{id}")
	public ResponseEntity findById(@PathVariable @NonNull String id) {
		// try {

		HashMap product = service.findById(id);
		HashMap reviewGeneralData = reviewService.getReviewGeneralData(id);

		HashMap result = new HashMap();
		result.put("product", product);
		result.put("reviews", reviewGeneralData);
		return new ResponseEntity<Object>(result, ok);
		// } catch (RestResponseException e) {
		// return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		// }
	}

}
