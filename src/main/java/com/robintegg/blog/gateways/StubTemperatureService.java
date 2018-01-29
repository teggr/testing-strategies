package com.robintegg.blog.gateways;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.robintegg.blog.domain.Measurement;
import com.robintegg.blog.domain.Temperature;

@Profile("stub-temperature")
@Service
public class StubTemperatureService implements TemperatureService {

	private int value = 5;
	private Measurement measurement = Measurement.CELCIUS;

	@Override
	public Temperature getTemperature() {
		return new Temperature(measurement, value);
	}

	public void setTemperature(int value, Measurement measurement) {
		this.value = value;
		this.measurement = measurement;
	}

}
