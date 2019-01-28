package com.adidas.reviewservice.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.reviewservice.dto.GenericResponse;
import com.adidas.reviewservice.dto.ReviewsDTO;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.repositories.ReviewRepository;
import com.adidas.reviewservice.services.ReviewService;
import com.adidas.reviewservice.util.GenericResponseUtils;

//@Secured
@RestController
@RequestMapping(path = "/api/review")
public class ReviewController {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	private ReviewService service;

	@GetMapping
	public ResponseEntity<GenericResponse> getReviews() throws EntityNotFoundException {
		Optional<List<Review>> productReviews = Optional.ofNullable(repository.findAll());
		if (!productReviews.isPresent()) {
			throw new EntityNotFoundException();
		}
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK(productReviews.get()));
	}

	@Cacheable(value = "reviews", key = "#productId")
	@GetMapping(value = "/{productId}")
	public ResponseEntity<GenericResponse> getReviewsByProductId(@PathVariable @NotNull String productId) {
		try {
			ReviewsDTO reviewsGeneralData = service.getReviewsGeneralData(productId);
			return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK(reviewsGeneralData));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(GenericResponseUtils.buildGenericResponseError(e));
		}
	}

	// @Secured
	@PostMapping
	public ResponseEntity<GenericResponse> insertReview(@RequestBody @Valid Review review) {
		Review reviewInserted = repository.insert(review);
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK(reviewInserted));
	}

	// @Secured
	@PutMapping
	public ResponseEntity<GenericResponse> updateReview(@RequestBody @Valid Review review) {
		Review reviewUpdated = repository.save(review);
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK(reviewUpdated));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericResponse> deleteReview(@PathVariable String id) {
		repository.deleteById(id);
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK("Deleted Review with id : " + id));
	}

}
