package com.adidas.productservice;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		SpringApplication.run(ProductServiceApplication.class, args);

		HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.adidas.co.uk/api/products/AC7836"))
				.version(HttpClient.Version.HTTP_2).GET().build();

		HttpClient client = HttpClient.newBuilder()
				// just to show off; HTTP/2 is the default
				.version(Version.HTTP_2).connectTimeout(Duration.ofSeconds(5)).build();

		String response = client.send(request, BodyHandlers.ofString()).body();

		System.out.println(response);
		// makeRestCall();
	}

	public static void makeRestCall() {
		RestTemplate http2Template = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
		try {
			HttpResponse<String> asJson = Unirest.get("https://http2.akamai.com/").header("Upgrade", "h2").asString();
			// HttpResponse<String> asString =
			// Unirest.get("https://www.adidas.co.uk/api/products/AC7836")
			// .header("Connection", "Upgrade, HTTP2-Settings").header("Upgrade",
			// "h2").asString();
			// System.out.println(asString.getBody());
			System.out.println(asJson.getBody());
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		// HashMap forEntity =
		// http2Template.getForObject("https://www.adidas.co.uk/api/products/AC7836",
		// HashMap.class);
		// String http2Response =
		// http2Template.getForObject("https://http2.akamai.com/", String.class);
		// System.out.println(http2Response);
		// System.out.println(forEntity);
	}

}
