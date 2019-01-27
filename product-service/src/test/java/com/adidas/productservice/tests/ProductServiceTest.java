package com.adidas.productservice.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.adidas.productservice.ProductServiceApplication;
import com.adidas.reviewservice.repositories.ReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class)
public class ProductServiceTest {

	// FIXME
	// Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException:
	// No qualifying bean of type
	// 'com.adidas.reviewservice.repositories.ReviewRepository' available: expected
	// at least 1 bean which qualifies as autowire candidate. Dependency
	// annotations:
	// {@org.springframework.beans.factory.annotation.Autowired(required=true)}

	// @Autowired
	ReviewRepository reviewRepository;

//	@Test
	public void shouldGetReviews_whenProductIdIs_AC7836() {
		// HashMap reviews = reviewRepository.getReviews("AC7836");
	}

	@Test
	public void givenAcceptingAllCertificatesUsing4_4_whenUsingRestTemplate_thenCorrect()
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		String url = "https://www.adidas.co.uk/api/products/AC7836";

		ResponseEntity<String> response = new RestTemplate(requestFactory).exchange(url, HttpMethod.GET, null,
				String.class);
		assertTrue(response.getStatusCode().value() == 200);
	}

}
