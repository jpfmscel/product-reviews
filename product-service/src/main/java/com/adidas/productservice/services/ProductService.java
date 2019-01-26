package com.adidas.productservice.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adidas.productservice.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	protected ProductRepository repository;

	public HashMap findById(String id) {
		return repository.findById(id);
	}

}
