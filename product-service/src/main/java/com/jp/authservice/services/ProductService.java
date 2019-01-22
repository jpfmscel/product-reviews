package com.jp.authservice.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.authservice.entities.Product;
import com.jp.authservice.exceptions.RestResponseException;
import com.jp.authservice.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	protected ProductRepository repository;

	public Product findByCode(String code) {
		return repository.findByCode(code);
	}

	public Product findById(String id) throws RestResponseException {
		Optional<Product> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		throw new RestResponseException("Product does not exist!");
	}

	public Product update(Product u) throws RestResponseException {
		return repository.save(u);
	}

	public Product insert(Product prod) throws RestResponseException {
		try {
			Product findByCode = findByCode(prod.getCode());
			if (findByCode != null) {
				throw new RestResponseException("This product code is already registered!");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return repository.save(prod);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}

	public void delete(String id) throws RestResponseException {
		Product user = findById(id);
		if (user != null)
			repository.deleteById(id);
	}
}
