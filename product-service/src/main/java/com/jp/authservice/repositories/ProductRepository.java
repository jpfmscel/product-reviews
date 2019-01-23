package com.jp.authservice.repositories;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProductRepository {

	public Object findById(String code) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://www.adidas.co.uk/api/products/" + code;
		Object result = restTemplate.getForObject(uri, Map.class);
		return result;
	}

}
