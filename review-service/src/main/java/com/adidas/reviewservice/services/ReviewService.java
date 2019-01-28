package com.adidas.reviewservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.reviewservice.dto.ReviewsDTO;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	public ReviewsDTO getReviewsGeneralData(String productId) throws EntityNotFoundException{
		Optional<List<Review>> reviewsByProductId = Optional.ofNullable(repository.findByProductId(productId));
		if (!reviewsByProductId.isPresent()) {
			throw new EntityNotFoundException();
		}

		Double averageScore = reviewsByProductId.stream().mapToDouble(Review::getScore).average().orElse(Double.NaN);
		ReviewsDTO reviewDTO = ReviewsDTO.builder().productId(productId).quantity(reviewsByProductId.stream().count())
				.averageScore(averageScore).build();

		return reviewDTO;
	}

}
