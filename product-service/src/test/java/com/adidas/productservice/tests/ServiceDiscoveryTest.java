package com.adidas.productservice.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.productservice.ProductServiceApplication;
import com.adidas.productservice.discovery.ServiceDiscovery;
import com.adidas.productservice.exceptions.ConfigurationException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class)
public class ServiceDiscoveryTest {

	@Autowired
	ServiceDiscovery serviceDiscovery;

	@Test
	public void shouldPass_whenReviewMicroserviceHostAndPortAreSetInDockerCompose() throws ConfigurationException {
		assertNotNull("Review microservice host or port not set.", serviceDiscovery.getMicroserviceBaseURL("review"));
	}

}
