package com.adidas.reviewservice.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.reviewservice.ReviewServiceApplication;
import com.adidas.reviewservice.discovery.ServiceDiscovery;
import com.adidas.reviewservice.exceptions.ConfigurationException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewServiceApplication.class)
public class ServiceDiscoveryTest {

	@Autowired
	ServiceDiscovery serviceDiscovery;

	@Test
	public void shouldPass_whenProductMicroserviceHostAndPortAreSetInDockerCompose() throws ConfigurationException {
		assertNotNull("Product microservice host or port not set.", serviceDiscovery.getMicroserviceBaseURL("product"));
	}

}
