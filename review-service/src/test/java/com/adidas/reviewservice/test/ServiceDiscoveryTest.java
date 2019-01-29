package com.adidas.reviewservice.test;

import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.reviewservice.ReviewServiceApplication;
import com.adidas.reviewservice.discovery.ServiceDiscovery;
import com.adidas.reviewservice.exceptions.ConfigurationException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewServiceApplication.class)
public class ServiceDiscoveryTest {

//	@Autowired
//	ServiceDiscovery serviceDiscovery;

	@Autowired
	MessageSource messageSource;
	
	@Test
	public void shouldPass_whenProductMicroserviceHostAndPortAreSetInDockerCompose() throws ConfigurationException {
//		assertNotNull("Product microservice host or port not set.", serviceDiscovery.getMicroserviceBaseURL("product"));
		
		System.out.println(messageSource.getMessage("welcome", null, Locale.ENGLISH));
		System.out.println(messageSource.getMessage("welcome", null, new Locale("pt")));
		System.out.println(messageSource.getMessage("welcome", null, new Locale("es")));
	}

}
