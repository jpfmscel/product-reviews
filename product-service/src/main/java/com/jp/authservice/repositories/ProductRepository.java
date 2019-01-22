package com.jp.authservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jp.authservice.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

	public Product findByCode(String code);

}
