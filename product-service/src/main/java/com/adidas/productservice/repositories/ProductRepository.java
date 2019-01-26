package com.adidas.productservice.repositories;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Repository
public class ProductRepository {

	public HashMap findById(String code) {
		String uri = "http://www.adidas.co.uk/api/products/" + code;
		HttpResponse<HashMap> product;
		try {
			product = Unirest.get(uri).header("accept", "application/json").asObject(HashMap.class);
			return product.getBody();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}

}
