package com.robintegg.blog.integration;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.robintegg.blog.domain.Outlook;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.repositories.WeatherSummaryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("stub-temperature")
public class WeatherSummaryRepositoryIT {

	@Autowired
	private WeatherSummaryRepository repository;

	@Test
	public void shouldSaveAndFetchFromLiveDb() {

		WeatherSummary saves = repository.save(new WeatherSummary(5, 41, Outlook.RAINY));

		WeatherSummary weatherSummary = repository.findAll().get(0);

		assertEquals(saves.getCelcius(), weatherSummary.getCelcius());
		assertEquals(saves.getFahrenheit(), weatherSummary.getFahrenheit());
		assertEquals(saves.getOutlook(), weatherSummary.getOutlook());

		assertEquals(saves.getTime(), weatherSummary.getTime());

	}

}
