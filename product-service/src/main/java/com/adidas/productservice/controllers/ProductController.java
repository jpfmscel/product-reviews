package com.adidas.productservice.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.productservice.dto.ReviewsDTO;
import com.adidas.productservice.entities.Review;
import com.adidas.productservice.exceptions.ConfigurationException;
import com.adidas.productservice.exceptions.EntityNotFoundException;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = "/{productId}")
//	@Cacheable(value = "products", key = "#productId")
	public ResponseEntity findById(@PathVariable @NonNull String productId) throws EntityNotFoundException,
			ConfigurationException, InterruptedException, ExecutionException, TimeoutException {

		HashMap product = new HashMap(0);

		CompletableFuture productFuture = CompletableFuture.supplyAsync(() -> {
			try {
				return service.findById(productId);
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		}).thenAcceptAsync(result -> setFutureValue(result, product));
		
		ReviewsDTO reviewGeneralData = reviewService.getReviewsGeneralData(productId);

		HashMap result = new HashMap();
		result.put("product", product);
		result.put("reviews", reviewGeneralData);
		return new ResponseEntity<Object>(result, ok);
	}

	private void setFutureValue(HashMap result, HashMap map) {
		map.clear();
		map.putAll(result);
	}
}
