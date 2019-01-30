package com.adidas.reviewservice.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.adidas.reviewservice.dto.ReviewsDTO;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.services.ReviewService;

public class ReviewProcessingTest {

	@Test
	public void shouldProcessReviewsAndReturnIntegerAverage() throws EntityNotFoundException {
		ReviewService revService = new ReviewService();

		List<Review> list = getReviews();
		ReviewsDTO processReviews = revService.processReviews("123", list);

		assertEquals(Long.valueOf(list.size()), processReviews.getQuantity());
		assertEquals(Double.valueOf(3), processReviews.getAverageScore());
		assertEquals("123", processReviews.getProductId());
	}

	@Test
	public void shouldProcessReviewsAndReturnDoubleAverage() throws EntityNotFoundException {
		ReviewService revService = new ReviewService();

		List<Review> list = getReviews();
		list.add(Review.builder().productId("123").score(1).build());
		ReviewsDTO processReviews = revService.processReviews("123", list);

		assertEquals(Long.valueOf(list.size()), processReviews.getQuantity());
		assertEquals(Double.valueOf(2.67), processReviews.getAverageScore());
		assertEquals("123", processReviews.getProductId());
	}

	@Test
	public void shouldProcessReviewsAndReturnEmptyReviewData() throws EntityNotFoundException {
		ReviewService revService = new ReviewService();

		List<Review> list = getReviews();
		ReviewsDTO processReviews = revService.processReviews("000", list);

		assertEquals(Long.valueOf(0), processReviews.getQuantity());
		assertEquals(Double.valueOf(0), processReviews.getAverageScore());
		assertEquals("000", processReviews.getProductId());
	}

	private List<Review> getReviews() {
		List<Review> reviews = new ArrayList<Review>();
		reviews.add(Review.builder().productId("123").score(3).build());
		reviews.add(Review.builder().productId("123").score(1).build());
		reviews.add(Review.builder().productId("123").score(2).build());
		reviews.add(Review.builder().productId("123").score(4).build());
		reviews.add(Review.builder().productId("123").score(5).build());
		return reviews;
	}

}
