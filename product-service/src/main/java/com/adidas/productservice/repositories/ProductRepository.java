package com.adidas.productservice.repositories;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class ProductRepository {

	private static final String API_PRODUCTS = "https://www.adidas.co.uk/api/products/";

	@Autowired
	private ObjectMapper jacksonObjectMapper;

	public HashMap findById(String code) {
		String uri = API_PRODUCTS + code;

		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).version(HttpClient.Version.HTTP_2).GET()
					.build();
			HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
			String response = client.send(request, BodyHandlers.ofString(Charset.forName("UTF-8"))).body();
			HashMap readValue = jacksonObjectMapper.readValue(response, HashMap.class);

			return readValue;
		} catch (IOException | InterruptedException | URISyntaxException e) {
			e.printStackTrace();
		}

		return null;
	}

}
