package com.adidas.productservice.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.adidas.productservice.facade.ReviewFacade;

@Service
public class ReviewService {

	@Autowired
	@Qualifier("reviewFacadeRestImpl")
	private ReviewFacade reviewFacade;

	public HashMap getReviewGeneralData(String productId) {
		HashMap reviews = reviewFacade.getReviews(productId);
		return reviews;
	}

}
