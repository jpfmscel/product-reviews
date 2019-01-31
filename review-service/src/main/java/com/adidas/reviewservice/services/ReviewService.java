package com.adidas.reviewservice.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.adidas.productservice.facade.ProductFacade;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.repositories.ReviewRepository;

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

	public void deleteReview(String id) throws EntityNotFoundException {
		Optional<Review> findById = repository.findById(id);
		if (findById.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Review with id = " + id + " not found.");
		}
	}
}
