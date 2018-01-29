package com.robintegg.blog.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.robintegg.blog.domain.Measurement;
import com.robintegg.blog.domain.Temperature;
import com.robintegg.blog.gateways.MockyRestTemperatureService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("mocky-temperature")
public class MockyRestWeatherServiceIT {

	@Autowired
	private MockyRestTemperatureService mockyRestTemperatureService;

	@Test
	public void shouldMakeACallToMockyWeatherService() {

		// given
		// http://www.mocky.io/v2/5a6e29c02e00003d22b8db6b

		// when
		Temperature temp = mockyRestTemperatureService.getTemperature();

		// then
		assertEquals(60, temp.as(Measurement.FAHRENHEIT));

	}

}
