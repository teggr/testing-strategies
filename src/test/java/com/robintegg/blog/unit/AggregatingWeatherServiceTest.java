package com.robintegg.blog.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.robintegg.blog.domain.Measurement;
import com.robintegg.blog.domain.Outlook;
import com.robintegg.blog.domain.Temperature;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.gateways.TemperatureService;
import com.robintegg.blog.services.AggregatingWeatherService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("stub-temperature")
public class AggregatingWeatherServiceTest {

	@MockBean
	private TemperatureService mockTemperatureService;

	@Autowired
	private AggregatingWeatherService aggregatingWeatherService;

	@Test
	public void shouldGenerateSummaryOfTemperatureAndOutlook() {

		// given
		when(mockTemperatureService.getTemperature()).thenReturn(new Temperature(Measurement.CELCIUS, 5));

		// when
		WeatherSummary summary = aggregatingWeatherService.getSummary();

		// then
		assertEquals(5, summary.getCelcius());
		assertEquals(41, summary.getFahrenheit());
		assertEquals(Outlook.SUNNY, summary.getOutlook());

	}

}
