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

import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.repositories.ReviewRepository;

//@Secured
@RestController
@RequestMapping(path = "/api/review")
public class ReviewController {

	@Autowired
	private ReviewRepository repository;

	@GetMapping(value = "/{product_id}")
	public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable @NotNull String product_id) {
		List<Review> productReviews = repository.findByProductId(product_id);
		return ResponseEntity.ok(productReviews);
	}

	// @Secured
	@PostMapping(value = "/{product_id}")
	public ResponseEntity<Review> insertReview(@PathVariable String product_id, @RequestBody @Valid Review review) {
		Review reviewInserted = repository.insert(review);
		return ResponseEntity.ok(reviewInserted);
	}

	@PutMapping(value = "/{product_id}")
	public ResponseEntity<Review> updateReview(@PathVariable String product_id, @RequestBody @Valid Review review) {
		Review reviewInserted = repository.save(review);
		return ResponseEntity.ok(reviewInserted);
	}

	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{product_id}/{id}")
	public ResponseEntity deleteReview(@PathVariable String product_id, @PathVariable String id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
