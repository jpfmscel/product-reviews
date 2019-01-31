package com.adidas.reviewservice.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.reviewservice.annotations.Secured;
import com.adidas.reviewservice.dto.GenericResponse;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.repositories.ReviewRepository;
import com.adidas.reviewservice.util.GenericResponseUtils;

@RestController
@RequestMapping(path = "/api/review")
public class ReviewController {

	@Autowired
	private ReviewRepository repository;

	@GetMapping
	@Cacheable(value = "reviews-all", key = "#productId")
	public ResponseEntity<GenericResponse> getReviews() throws EntityNotFoundException {
		List<Review> productReviews = repository.findAll();
		if (productReviews.isEmpty()) {
			throw new EntityNotFoundException("No reviews found.");
		}
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK(productReviews));
	}

	@Secured
	@PostMapping
	public ResponseEntity<GenericResponse> insertReview(@RequestBody @Valid Review review) {
		Review reviewInserted = repository.insert(review);
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK(reviewInserted));
	}

	@Secured
	@PutMapping
	public ResponseEntity<GenericResponse> updateReview(@RequestBody @Valid Review review) {
		Review reviewUpdated = repository.save(review);
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK(reviewUpdated));
	}

	@Secured
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GenericResponse> deleteReview(@PathVariable String id) throws EntityNotFoundException {
		Optional<Review> findById = repository.findById(id);
		if (findById.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Review with id = " + id + " not found.");
		}
		return ResponseEntity.ok(GenericResponseUtils.buildGenericResponseOK("Deleted Review with id : " + id));
	}

	@CacheEvict(allEntries = true)
	@Scheduled(fixedDelay = 1000)
	public void cacheEvict() {
	}

}
