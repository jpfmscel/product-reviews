package com.jp.authservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jp.authservice.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmail(String email);

	public User findByName(String name);
}
