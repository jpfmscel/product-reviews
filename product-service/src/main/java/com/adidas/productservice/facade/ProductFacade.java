package com.adidas.productservice.facade;

import java.util.HashMap;

import com.adidas.productservice.exceptions.EntityNotFoundException;

public interface ProductFacade {

	public HashMap getProduct(String productId) throws EntityNotFoundException;
}
