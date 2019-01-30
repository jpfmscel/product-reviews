package com.adidas.reviewservice.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.adidas.reviewservice.dto.ReviewsDTO;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.services.ReviewService;

public class ReviewTest {

	@Test
	public void shouldProcessReviewsAndReturnIntegerAverage() throws EntityNotFoundException {
		ReviewService revService = new ReviewService();

		List<Review> list = getReviews();
		ReviewsDTO processReviews = revService.processReviews("123", list);

		assertEquals(processReviews.getQuantity(), Long.valueOf(list.size()));
		assertEquals(processReviews.getAverageScore(), Double.valueOf(3));
		assertEquals(processReviews.getProductId(), "123");
	}

	@Test
	public void shouldProcessReviewsAndReturnDoubleAverage() throws EntityNotFoundException {
		ReviewService revService = new ReviewService();

		List<Review> list = getReviews();
		list.add(Review.builder().productId("123").score(1).build());
		ReviewsDTO processReviews = revService.processReviews("123", list);
		
		assertEquals(processReviews.getQuantity(), Long.valueOf(list.size()));
		assertEquals(processReviews.getAverageScore(), Double.valueOf(2.67));
		assertEquals(processReviews.getProductId(), "123");
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
