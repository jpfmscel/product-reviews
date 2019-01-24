package com.adidas.reviewservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.reviewservice.dto.ReviewsDTO;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	public ReviewsDTO getReviewsGeneralData(String productId) {
		List<Review> reviewsByProductId = repository.findByProductId(productId);
		Double averageScore = reviewsByProductId.stream().mapToDouble(Review::getScore).average().orElse(Double.NaN);
		ReviewsDTO reviewDTO = ReviewsDTO.builder().productId(productId).quantity(reviewsByProductId.size())
				.averageScore(averageScore).build();

		return reviewDTO;
	}

}
