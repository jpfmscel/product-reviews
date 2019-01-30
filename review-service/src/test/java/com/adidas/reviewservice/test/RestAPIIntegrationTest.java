package com.adidas.reviewservice.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.HashMap;

import org.springframework.http.MediaType;

import com.adidas.reviewservice.dto.GenericResponse;
import com.adidas.reviewservice.util.GenericResponseUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestAPIIntegrationTest {

	default public GenericResponse post(String url, Object body)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {

		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).version(HttpClient.Version.HTTP_2)
				.headers("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
				.POST(HttpRequest.BodyPublishers.ofString(jacksonObjectMapper.writeValueAsString(body))).build();
		HttpResponse<String> response = sendRequest(request);
		HashMap readValue = jacksonObjectMapper.readValue(response.body(), HashMap.class);
		if (response.statusCode() >= 400) {
			return GenericResponseUtils.buildGenericResponseError(readValue);
		}
		return GenericResponseUtils.buildGenericResponseOK(readValue);
	}

	default public GenericResponse delete(String url)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {

		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).version(HttpClient.Version.HTTP_2)
				.DELETE().build();
		HttpResponse<String> response = sendRequest(request);
		HashMap readValue = jacksonObjectMapper.readValue(response.body(), HashMap.class);
		if (response.statusCode() >= 400) {
			return GenericResponseUtils.buildGenericResponseError(readValue);
		}
		return GenericResponseUtils.buildGenericResponseOK(readValue);
	}
	
	default public GenericResponse get(String url)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {

		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).version(HttpClient.Version.HTTP_2).GET()
				.build();
		HttpResponse<String> response = sendRequest(request);
		HashMap readValue = jacksonObjectMapper.readValue(response.body(), HashMap.class);
		if (response.statusCode() >= 400) {
			return GenericResponseUtils.buildGenericResponseError(readValue);
		}
		return GenericResponseUtils.buildGenericResponseOK(readValue);
	}

	default public GenericResponse put(String url, Object body)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException, URISyntaxException {

		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).version(HttpClient.Version.HTTP_2)
				.headers("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
				.PUT(HttpRequest.BodyPublishers.ofString(jacksonObjectMapper.writeValueAsString(body))).build();
		HttpResponse<String> response = sendRequest(request);
		HashMap readValue = jacksonObjectMapper.readValue(response.body(), HashMap.class);
		if (response.statusCode() >= 400) {
			return GenericResponseUtils.buildGenericResponseError(readValue);
		}
		return GenericResponseUtils.buildGenericResponseOK(readValue);
	}

	public default HttpResponse<String> sendRequest(HttpRequest request)
			throws URISyntaxException, IOException, InterruptedException {

		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString(Charset.forName("UTF-8")));
		return response;
	}
}
