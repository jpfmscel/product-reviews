package com.adidas.productservice.tests;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.productservice.ProductServiceApplication;
import com.adidas.productservice.exceptions.ConfigurationException;
import com.adidas.productservice.exceptions.EntityNotFoundException;
import com.adidas.productservice.facade.ReviewFacade;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class)
public class ReviewTest {

	@Autowired
	@Qualifier("reviewFacadeRestImpl")
	ReviewFacade reviewFacade;

	@Test
	public void shouldGetReviews_whenProductIdIs_AC7836() throws EntityNotFoundException, ConfigurationException {
		HashMap reviews = reviewFacade.getReviews("AC7836");
	}

}
