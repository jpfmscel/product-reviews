package com.adidas.productservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.adidas.productservice.entities.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

	public List<Review> findByProductId(String productId);

}
