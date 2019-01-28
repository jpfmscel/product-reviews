package com.adidas.productservice.facade;

import java.util.HashMap;

import com.adidas.productservice.exceptions.ConfigurationException;
import com.adidas.productservice.exceptions.EntityNotFoundException;

public interface ReviewFacade {

	public HashMap getReviews(String productId) throws EntityNotFoundException, ConfigurationException;
}
