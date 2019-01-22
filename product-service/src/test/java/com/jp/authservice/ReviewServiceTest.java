package com.jp.authservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jp.authservice.entities.Review;
import com.jp.authservice.services.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewServiceTest implements RestIntegrationTest {

	@Autowired
	ProductService service;

	@Test
	public void shouldCreateReview_whenProductExists() {
		Review r1 = Review.builder().title("Good Review").description("Such a good product!").score(5).build();
	}

}
