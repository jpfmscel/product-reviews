package com.adidas.productservice.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.adidas.productservice.exceptions.EntityNotFoundException;
import com.adidas.productservice.facade.ProductFacade;

@Service
public class ProductService {

	@Autowired
	@Qualifier("productFacadeRestImpl")
	protected ProductFacade facade;

	public HashMap findById(String id) throws EntityNotFoundException {
		return facade.getProduct(id);
	}

}
