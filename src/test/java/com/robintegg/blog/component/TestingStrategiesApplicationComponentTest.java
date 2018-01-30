package com.robintegg.blog.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.robintegg.blog.domain.Measurement;
import com.robintegg.blog.domain.Outlook;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.gateways.StubTemperatureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("stub-temperature")
public class TestingStrategiesApplicationComponentTest {

	// wiremock or mocky

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private StubTemperatureService stubTemperatureService;

	@Test
	public void shouldReturnServicesWith200Ok() {

		// given
		stubTemperatureService.setTemperature(28, Measurement.CELCIUS);

		// when
		WeatherSummary summary = testRestTemplate.getForObject("/", WeatherSummary.class);

		// then
		assertEquals(28, summary.getCelcius());
		assertEquals(Outlook.SUNNY, summary.getOutlook());

	}

}
