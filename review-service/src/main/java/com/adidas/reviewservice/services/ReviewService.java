package com.adidas.reviewservice.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.facade.ProductFacade;
import com.adidas.reviewservice.repositories.ReviewRepository;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	@Qualifier("productFacadeRestImpl")
	private ProductFacade productFacade;

	public List<Review> findAll() {
		return repository.findAll();
	}

	public Review insert(@Valid Review review) throws EntityNotFoundException {
		productFacade.getProduct(review.getProductId());
		return repository.insert(review);
	}

	public Review save(@Valid Review review) throws EntityNotFoundException {
		productFacade.getProduct(review.getProductId());
		return repository.save(review);
	}

	public void deleteReview(String reviewId) throws EntityNotFoundException {
		if (!StringUtils.hasText(reviewId)) {
			throw new EntityNotFoundException("Review ID not provided.");
		}
		Optional<Review> findById = repository.findById(reviewId);
		if (findById.isPresent()) {
			repository.deleteById(reviewId);
		} else {
			throw new EntityNotFoundException("Review with id = " + reviewId + " not found.");
		}
	}
}
