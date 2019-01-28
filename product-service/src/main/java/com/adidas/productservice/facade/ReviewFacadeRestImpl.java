package com.adidas.productservice.facade;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.adidas.productservice.discovery.ServiceDiscovery;
import com.adidas.productservice.exceptions.ConfigurationException;

@Component
public class ReviewFacadeRestImpl implements ReviewFacade {

	@Autowired
	private ServiceDiscovery serviceDiscovery;

	@Override
	public HashMap getReviews(String productId) {

		String microserviceBaseURL;
		try {
			microserviceBaseURL = serviceDiscovery.getMicroserviceBaseURL("review");
			ResponseEntity<HashMap> response = new RestTemplate().getForEntity(microserviceBaseURL + "/" + productId,
					HashMap.class);
			return response.getBody();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return null;
	}

}