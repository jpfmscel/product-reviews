package com.adidas.productservice.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.adidas.productservice.dto.ReviewsDTO;
import com.adidas.productservice.entities.Review;
import com.adidas.productservice.exceptions.EntityNotFoundException;
import com.adidas.productservice.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public ReviewsDTO getReviewsGeneralData(String productId) throws EntityNotFoundException {
		List<Review> reviewsByProductId = reviewRepository.findByProductId(productId);
		if (reviewsByProductId.isEmpty()) {
			throw new EntityNotFoundException("No reviews found for productId = " + productId);
		}
		ReviewsDTO reviewDTO = processReviews(productId, reviewsByProductId);
		return reviewDTO;
	}

	public ReviewsDTO processReviews(String productId, List<Review> reviewsByProductId) {
		if (!StringUtils.hasText(productId)) {
			throw new IllegalArgumentException("Invalid Product ID");
		}

		Double averageScore = reviewsByProductId.stream().filter(review -> productId.equals(review.getProductId()))
				.mapToDouble(Review::getScore).average().orElse(Double.valueOf(0));

		averageScore = round(averageScore, 2);

		long count = reviewsByProductId.stream().filter(review -> productId.equals(review.getProductId())).count();

		ReviewsDTO reviewDTO = ReviewsDTO.builder().productId(productId).quantity(count).averageScore(averageScore)
				.build();

		return reviewDTO;
	}

	private double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
