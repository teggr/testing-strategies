package com.robintegg.blog.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.robintegg.blog.domain.Measurement;
import com.robintegg.blog.domain.Outlook;
import com.robintegg.blog.domain.Temperature;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.gateways.TemperatureService;
import com.robintegg.blog.repositories.WeatherSummaryRepository;

@Transactional
@Service
public class AggregatingWeatherService implements WeatherService {

	private final TemperatureService temperatureService;
	private final WeatherSummaryRepository weatherSummaryRepository;

	public AggregatingWeatherService(TemperatureService temperatureService,
			WeatherSummaryRepository weatherSummaryRepository) {
		this.temperatureService = temperatureService;
		this.weatherSummaryRepository = weatherSummaryRepository;
	}

	@Override
	public WeatherSummary getSummary() {

		// get from another service
		Temperature temperature = this.temperatureService.getTemperature();

		// create the summary
		WeatherSummary weatherSummary = new WeatherSummary(temperature.as(Measurement.CELCIUS),
				temperature.as(Measurement.FAHRENHEIT), Outlook.SUNNY);

		// record the summary
		weatherSummary = weatherSummaryRepository.save(weatherSummary);

		return weatherSummary;

	}

}
