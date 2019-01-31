package com.adidas.productservice.facade;

import java.util.HashMap;

import com.adidas.reviewservice.exceptions.EntityNotFoundException;

public interface ProductFacade {

	public HashMap getProduct(String productId) throws EntityNotFoundException;
}
