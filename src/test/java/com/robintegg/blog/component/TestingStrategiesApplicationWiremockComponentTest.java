package com.robintegg.blog.component;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.robintegg.blog.domain.Outlook;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.gateways.MockyRestTemperatureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("mocky-temperature")
public class TestingStrategiesApplicationWiremockComponentTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);

	@BeforeClass
	public static void setupTest() {
		MockyRestTemperatureService.MOCKY_GET_URL = "http://localhost:8089/";
	}

	@Test
	public void shouldReturnServicesWith200Ok() {

		// given
		stubFor(get(urlEqualTo("/"))
				.willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
						.withBody("{\"measurement\":\"FAHRENHEIT\",\"value\":41}")));

		// when
		WeatherSummary summary = testRestTemplate.getForObject("/", WeatherSummary.class);

		// then
		assertEquals(5, summary.getCelcius());
		assertEquals(Outlook.SUNNY, summary.getOutlook());

	}

}
