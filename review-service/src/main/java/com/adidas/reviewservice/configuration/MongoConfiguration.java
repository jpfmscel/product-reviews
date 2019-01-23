package com.adidas.reviewservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Configuration
public class MongoConfiguration {

	@Value("${mongo.host}")
	private String host;

	@Value("${mongo.port}")
	private Integer port;

	public @Bean MongoClientFactoryBean mongo() {
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
		mongo.setHost(host);
		mongo.setPort(port);
		return mongo;
	}
}
