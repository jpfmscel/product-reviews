package com.adidas.productservice.tests;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.productservice.ProductServiceApplication;
import com.adidas.reviewservice.repositories.ReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class)
public class ProductServiceTest {

	@Autowired
	ReviewRepository reviewRepository;

	@Test
	public void shouldGetReviews_whenProductIdIs_AC7836() {
		HashMap reviews = reviewRepository.getReviews("AC7836");
	}

}
