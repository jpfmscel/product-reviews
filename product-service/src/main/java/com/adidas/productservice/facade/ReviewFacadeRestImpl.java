package com.adidas.productservice.facade;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.adidas.productservice.discovery.ServiceDiscovery;
import com.adidas.productservice.exceptions.ConfigurationException;
import com.adidas.productservice.exceptions.EntityNotFoundException;

@Component
public class ReviewFacadeRestImpl implements ReviewFacade {

	@Autowired
	private ServiceDiscovery serviceDiscovery;

	@Override
	public HashMap getReviews(String productId) throws EntityNotFoundException, ConfigurationException {

		String microserviceBaseURL;
		microserviceBaseURL = serviceDiscovery.getMicroserviceBaseURL("review");
		ResponseEntity<HashMap> response = new RestTemplate().getForEntity(microserviceBaseURL + "/" + productId,
				HashMap.class);

		if (response.getBody().get("result") == null) {
			throw new EntityNotFoundException();
		}
		
		return response.getBody();

	}

}
