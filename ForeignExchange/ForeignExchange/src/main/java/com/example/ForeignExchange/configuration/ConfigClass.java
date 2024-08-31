package com.example.ForeignExchange.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigClass {

	@Bean
	public RestTemplate restTemplate() {
		// we could further modify this ideally we us resttemplate builder but as there
		// is no specific requirement so we are not using it
		return new RestTemplate();
	}
}
