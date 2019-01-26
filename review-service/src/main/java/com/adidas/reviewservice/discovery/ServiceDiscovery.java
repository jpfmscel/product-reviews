package com.adidas.reviewservice.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.adidas.reviewservice.exceptions.ConfigurationException;

@Service
public class ServiceDiscovery {

	@Autowired
	private Environment env;

	private final String ENV_VARIABLE_PREFIX = "MICROSERVICE_";
	private final String ENV_VARIABLE_MS_HOST = "_HOST";
	private final String ENV_VARIABLE_MS_PORT = "_PORT";

	public String getMicroserviceBaseURL(String domainName) throws ConfigurationException {
		String microserviceHostKey = ENV_VARIABLE_PREFIX + domainName.toUpperCase() + ENV_VARIABLE_MS_HOST;
		String microServiceHost = env
				.getProperty(microserviceHostKey);
		
		String microservicePortKey = ENV_VARIABLE_PREFIX + domainName.toUpperCase() + ENV_VARIABLE_MS_PORT;
		String microServicePort = env
				.getProperty(microservicePortKey);

		if (!StringUtils.hasText(microServiceHost)) {
			throw new ConfigurationException("Microservice host not set. Ex.: http://localhost");
		}

		if (!StringUtils.hasText(microServicePort)) {
			throw new ConfigurationException("Microservice port not set. Ex.: 8080");
		}

		StringBuffer baseURL = new StringBuffer();
		baseURL.append(microServiceHost);
		baseURL.append(":");
		baseURL.append(microServicePort);
		baseURL.append("/api/");
		baseURL.append(domainName);

		// Result should be http://localhost:8080/<domainName>
		return baseURL.toString();
	}
}
