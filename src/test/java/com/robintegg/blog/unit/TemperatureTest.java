package com.robintegg.blog.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.robintegg.blog.domain.Measurement;
import com.robintegg.blog.domain.Temperature;

public class TemperatureTest {

	@Test
	public void shouldConvertBetweenCelciusandFahrenheit() {

		Temperature temp = new Temperature(Measurement.CELCIUS, 5);

		assertEquals(5, temp.as(Measurement.CELCIUS));
		assertEquals(41, temp.as(Measurement.FAHRENHEIT));

		temp = new Temperature(Measurement.FAHRENHEIT, 41);
		assertEquals(41, temp.as(Measurement.FAHRENHEIT));
		assertEquals(5, temp.as(Measurement.CELCIUS));

	}

}
