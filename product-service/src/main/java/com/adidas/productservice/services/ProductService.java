package com.adidas.productservice.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.adidas.productservice.exceptions.EntityNotFoundException;
import com.adidas.productservice.facade.ProductFacade;

@Service
public class ProductService {

	@Autowired
	@Qualifier("productFacadeRestImpl")
	private ProductFacade facade;

	@Cacheable(value = "products", key="#productId")
	public HashMap findById(String productId) throws EntityNotFoundException {
		return facade.getProduct(productId);
	}

}
