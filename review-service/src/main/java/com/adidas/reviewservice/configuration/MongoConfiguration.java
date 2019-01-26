package com.adidas.reviewservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

@Configuration
public class MongoConfiguration {

	private static final String DOCKER_MONGO_HOST = "MONGO_HOST";
	private static final String DOCKER_MONGO_PORT = "MONGO_PORT";

	@Value("${mongo.host}")
	private String host;

	@Value("${mongo.port}")
	private Integer port;

	@Autowired
	private Environment env;

	public @Bean MongoClientFactoryBean mongo() {
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();

		String dockerMongoHost = env.getProperty(DOCKER_MONGO_HOST);
		if (dockerMongoHost != null) {
			mongo.setHost(dockerMongoHost);
		} else {
			mongo.setHost(host);
		}

		String dockerMongoPort = env.getProperty(DOCKER_MONGO_PORT);
		if (dockerMongoPort != null) {
			mongo.setPort(Integer.valueOf(dockerMongoPort));
		} else {
			mongo.setPort(port);
		}
		
		mongo.setPort(port);
		return mongo;
	}
}
