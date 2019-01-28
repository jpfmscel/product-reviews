package com.adidas.reviewservice.test;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.reviewservice.ReviewServiceApplication;
import com.adidas.reviewservice.dto.ReviewsDTO;
import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.exceptions.EntityNotFoundException;
import com.adidas.reviewservice.repositories.ReviewRepository;
import com.adidas.reviewservice.services.ReviewService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewServiceApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReviewServiceApplicationTests {

	static Review review;

	@Autowired
	ReviewRepository repository;

	@Autowired
	ReviewService service;

	@BeforeClass
	public static void init() {
		review = Review.builder().productId("123456").title("Nice product Junit").description("Review JUnit Test")
				.score(3).build();
	}

	@Test
	public void a_insertReview() {
		Review insertedReview = repository.insert(review);
		assertTrue("Review was not inserted.", insertedReview.getId() != null);
		review.setId(insertedReview.getId());
	}

	@Test
	public void b_findReview() {
		Optional<Review> opReview = repository.findById(review.getId());
		assertTrue("Review not found.", opReview.isPresent());
	}

	@Test
	public void c_updateReview() {
		String expectedTitle = review.getTitle() + " UPDATED";
		review.setTitle(expectedTitle);
		Review updatedReview = repository.save(review);

		assertTrue("Review was not updated.", updatedReview.getTitle().equals(expectedTitle));
	}

	@Test
	public void d_getReviewsGeneralData() throws EntityNotFoundException {
		ReviewsDTO reviewsGeneralData = service.getReviewsGeneralData(review.getProductId());

		assertTrue("Product ID is not the same.", reviewsGeneralData.getProductId().equals(review.getProductId()));
		assertTrue("Average score is not correct.", reviewsGeneralData.getAverageScore().compareTo(new Double(3)) == 0);
		assertTrue("Quantity of reviews is not correct.", reviewsGeneralData.getQuantity().intValue() == 1);
	}

	@Test
	public void e_deleteReview() {
		repository.deleteById(review.getId());
		Optional<Review> opReview = repository.findById(review.getId());
		assertTrue("Review not deleted.", !opReview.isPresent());
	}
}
