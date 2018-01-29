package com.robintegg.blog.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.robintegg.blog.domain.Outlook;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.repositories.WeatherSummaryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WeatherSummaryRepositoryTest {

	@Autowired
	private WeatherSummaryRepository repo;

	@Test
	public void shouldSaveSummary() {

		WeatherSummary saves = repo.save(new WeatherSummary(5, 41, Outlook.RAINY));

		WeatherSummary weatherSummary = repo.findAll().get(0);

		assertEquals(saves.getCelcius(), weatherSummary.getCelcius());
		assertEquals(saves.getFahrenheit(), weatherSummary.getFahrenheit());
		assertEquals(saves.getOutlook(), weatherSummary.getOutlook());

		assertEquals(saves.getTime(), weatherSummary.getTime());

	}

}
