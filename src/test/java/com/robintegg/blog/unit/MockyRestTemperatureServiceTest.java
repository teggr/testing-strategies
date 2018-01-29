package com.robintegg.blog.unit;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.robintegg.blog.domain.Measurement;
import com.robintegg.blog.domain.Temperature;
import com.robintegg.blog.gateways.MockyRestTemperatureService;

@RunWith(SpringRunner.class)
@RestClientTest(MockyRestTemperatureService.class)
@ActiveProfiles("mocky-temperature")
public class MockyRestTemperatureServiceTest {

	@Autowired
	private MockyRestTemperatureService temperatureService;

	@Autowired
	private MockRestServiceServer server;

	@Test
	public void shouldMakeACallToMockyService() {

		// given
		server.expect(requestTo(MockyRestTemperatureService.MOCKY_GET_URL)).andRespond(withSuccess()
				.contentType(MediaType.APPLICATION_JSON).body("{\"measurement\":\"FAHRENHEIT\",\"value\":60}"));

		// when
		Temperature temperature = temperatureService.getTemperature();

		// then
		assertEquals(60, temperature.as(Measurement.FAHRENHEIT));

	}

}
