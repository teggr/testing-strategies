package com.robintegg.blog.gateways;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.robintegg.blog.domain.Temperature;

@Profile("mocky-temperature")
@Service
public class MockyRestTemperatureService implements TemperatureService {

	public static String MOCKY_GET_URL = "http://www.mocky.io/v2/5a6e29c02e00003d22b8db6b";

	private final RestTemplate restTemplate;

	public MockyRestTemperatureService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Temperature getTemperature() {
		return restTemplate.getForObject(MOCKY_GET_URL, Temperature.class);
	}

}
