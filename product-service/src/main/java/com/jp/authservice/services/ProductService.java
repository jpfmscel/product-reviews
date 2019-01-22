package com.jp.authservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.authservice.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	protected ProductRepository repository;

	public Object findById(String id){
		return repository.findById(id);
	}

}
