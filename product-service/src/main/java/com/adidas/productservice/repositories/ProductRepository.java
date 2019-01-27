package com.adidas.productservice.repositories;

import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Repository
public class ProductRepository {

	private static final String API_PRODUCTS = "https://www.adidas.co.uk/api/products/";

	// TODO
	public HashMap findById(String code) {
		String uri = API_PRODUCTS + code;
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
