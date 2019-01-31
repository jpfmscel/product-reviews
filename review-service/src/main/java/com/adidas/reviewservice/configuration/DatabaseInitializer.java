package com.adidas.reviewservice.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adidas.reviewservice.entities.Review;
import com.adidas.reviewservice.repositories.ReviewRepository;

@Component
public class DatabaseInitializer {

	private Logger LOG = Logger.getLogger(DatabaseInitializer.class);

	@Autowired
	private ReviewRepository reviewRepository;

	@PostConstruct
	public void init() {
		if (reviewRepository.findAll().isEmpty()) {
			LOG.info("Starting database seeding...");
			reviewRepository.insert(getReviews());
			LOG.info(getReviews().size() + " Reviews inserted into the database.");
		}
	}

	public List<Review> getReviews() {
		List<Review> list = new ArrayList<>(0);
		list.add(Review.builder().productId("M20324").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("M20324").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("M20324").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("AC7836").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("AC7836").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("B42000").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("BB5476").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());
		list.add(Review.builder().productId("C77154").title("title").description("desc")
				.score(getRandomNumberBetween(0, 5)).build());

		return list;
	}

	public Integer getRandomNumberBetween(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt((maximum - minimum) + 1);
	}
}
