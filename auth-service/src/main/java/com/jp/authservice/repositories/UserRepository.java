package com.jp.authservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jp.authservice.entities.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmail(String email);

	public User findByName(String name);
}
