package com.robintegg.blog.domain;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class WeatherSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int celcius;

	private Outlook outlook;

	private int fahrenheit;

	private Instant time;

	public WeatherSummary(int celcius, int fahrenheit, Outlook outlook) {
		super();
		this.celcius = celcius;
		this.fahrenheit = fahrenheit;
		this.outlook = outlook;
		this.time = Instant.now();
	}

	@JsonCreator
	public WeatherSummary(@JsonProperty("celcius") int celcius, @JsonProperty("fahrenheit") int fahrenheit,
			@JsonProperty("outlook") Outlook outlook, @JsonProperty("time") Instant time) {
		super();
		this.celcius = celcius;
		this.fahrenheit = fahrenheit;
		this.outlook = outlook;
		this.time = time;
	}

	private WeatherSummary() {
		// for JPA
	}

	public Outlook getOutlook() {
		return outlook;
	}

	public int getCelcius() {
		return celcius;
	}

	public int getFahrenheit() {
		return fahrenheit;
	}

	public Instant getTime() {
		return time;
	}

}
