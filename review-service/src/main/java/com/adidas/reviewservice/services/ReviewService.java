package com.adidas.reviewservice.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	public ReviewsDTO getReviewsGeneralData(String productId) throws EntityNotFoundException {
		Optional<List<Review>> reviewsByProductId = Optional.ofNullable(repository.findByProductId(productId));
		if (!reviewsByProductId.isPresent()) {
			throw new EntityNotFoundException();
		}
		ReviewsDTO reviewDTO = processReviews(productId, reviewsByProductId.get());
		return reviewDTO;
	}

	public ReviewsDTO processReviews(String productId, List<Review> reviewsByProductId) {
		Double averageScore = reviewsByProductId.stream().mapToDouble(Review::getScore).average().orElse(Double.NaN);

		if (averageScore.compareTo(Double.NaN) != 0) {
			averageScore = round(averageScore, 2);
		}

		ReviewsDTO reviewDTO = ReviewsDTO.builder().productId(productId).quantity(reviewsByProductId.stream().count())
				.averageScore(averageScore).build();
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
