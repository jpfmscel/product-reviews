package com.adidas.reviewservice.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.reviewservice.dto.ReviewsDTO;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.repositories.ReviewRepository;
import com.adidas.reviewservice.services.ReviewService;

//@Secured
@RestController
@RequestMapping(path = "/api/review")
public class ReviewController {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private ReviewService service;

	@GetMapping
	public ResponseEntity<List<Review>> getReviews() {
		List<Review> productReviews = repository.findAll();
		return ResponseEntity.ok(productReviews);
	}

	@GetMapping(value = "/{product_id}")
	public ResponseEntity<ReviewsDTO> getReviewsByProductId(@PathVariable @NotNull String product_id) {
		ReviewsDTO reviewsGeneralData = service.getReviewsGeneralData(product_id);
		return ResponseEntity.ok(reviewsGeneralData);
	}

	// @Secured
	@PostMapping
	public ResponseEntity<Review> insertReview(@RequestBody @Valid Review review) {
		Review reviewInserted = repository.insert(review);
		return ResponseEntity.ok(reviewInserted);
	}

	// @Secured
	@PutMapping
	public ResponseEntity<Review> updateReview(@RequestBody @Valid Review review) {
		Review reviewInserted = repository.save(review);
		return ResponseEntity.ok(reviewInserted);
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity deleteReview(@PathVariable String id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
