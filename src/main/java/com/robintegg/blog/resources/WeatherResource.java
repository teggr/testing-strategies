package com.robintegg.blog.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robintegg.blog.domain.WeatherException;
import com.robintegg.blog.domain.WeatherSummary;
import com.robintegg.blog.services.WeatherService;

@RestController
@RequestMapping("/")
public class WeatherResource {

	private final WeatherService weatherService;

	public WeatherResource(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping
	public ResponseEntity<WeatherSummary> get() {
		return ResponseEntity.ok(weatherService.getSummary());
	}

	@ExceptionHandler(WeatherException.class)
	public ResponseEntity<?> handleWeatherException() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
