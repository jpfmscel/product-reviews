package com.adidas.productservice.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.adidas.productservice.exceptions.ConfigurationException;
import com.adidas.productservice.exceptions.EntityNotFoundException;
import com.adidas.productservice.facade.ReviewFacade;

@Service
public class ReviewService {

	@Autowired
	@Qualifier("reviewFacadeRestImpl")
	private ReviewFacade reviewFacade;

	public HashMap getReviewGeneralData(String productId) throws EntityNotFoundException, ConfigurationException {
		return reviewFacade.getReviews(productId);
	}

}
